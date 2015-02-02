package org.usfirst.frc.team79.robot.camera;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;


public class VisionService {
	
	public static final String NATIVE_LIBRARY_PATH = "/usr/local/lib/lib_OpenCV/java/libopencv_java2410.so";
	
	private static final int CAP_PROP_BRIGHTNESS = 10;
	private static final int CAP_PROP_CONTRAST = 11;
	private static final int CAP_PROP_SATURATION = 12;
	private static final int CAP_PROP_EXPOSURE = 15;
	private static final int CAP_PROP_AUTO_EXPOSURE = 21;
	
	private static final int FRAME_WIDTH = 320;
	private static final int FRAME_HEIGHT = 240;
	private static final double FPS = 7.5;
	
	// Threshold Constants
	public static final double HUE_MIN = 60;
	public static final double HUE_MAX = 100;
	public static final double SAT_MIN = 90;
	public static final double SAT_MAX = 255;
	public static final double VAL_MIN = 20;
	public static final double VAL_MAX = 255;
	
	// Colors
	static final Scalar COLOR_GREEN = new Scalar(100, 255, 0);
	static final Scalar COLOR_BLUE = new Scalar(255, 191, 0);
	static final Scalar COLOR_YELLOW = new Scalar(0, 255, 255);
	static final Scalar COLOR_RED = new Scalar(0, 0, 255);
	
	private static final double BUFFER_FLUSH_DELAY = 12; // seconds
	
	private static VisionService service;
	private static VideoCapture vcap;
	private static int videoStreamAddress = 0; // represents /dev/video0
	
	private static Object rawImgMutex = new Object();
	private static Object measurementMutex = new Object();
	
	private static volatile boolean leftExists = false;
	private static volatile boolean rightExists = false;
	private static volatile double errorFromRight = 0;
	
	private static volatile Mat frame = new Mat();
	private static volatile AtomicBoolean cameraConnected = new AtomicBoolean(false);
	private static volatile AtomicBoolean processingImage = new AtomicBoolean(false);
	
	private VisionService(){
		// Start video capture thread
		Thread videoCaptureThread = new Thread(new VideoCaptureRunnable());
		Thread imgprocThread = new Thread(new ImageProcessingRunnable());
		videoCaptureThread.start();
		imgprocThread.start();
	}
	
	public static VisionService getInstance(){
		if(service == null){
			service = new VisionService();
		}
		
		return service;
	}
	
	public boolean cameraConnected(){
		return cameraConnected.get();
	}
	
	public boolean processingImage(){
		return processingImage.get();
	}
	
	public boolean leftTargetExists(){
		synchronized(measurementMutex){
			return leftExists;
		}
	}
	
	public boolean rightTargetExists(){
		synchronized(measurementMutex){
			return rightExists;
		}
	}
	
	public double getErrorFromRightTarget(){
		synchronized(measurementMutex){
			return errorFromRight;
		}
	}
	
	private class VideoCaptureRunnable implements Runnable {

		@Override
		public void run() {
			// Create timer variables
			long start, end, bufferStart, bufferEnd;
			start = System.currentTimeMillis();
			
			// Initialize VideoCapture object
			vcap = new VideoCapture();

			System.out.println();
			System.out.println("USB Webcam Server is trying to connect...");
			System.out.println();
			
			int count = 1;
			
			//open the video stream and make sure it's opened
			//We specify desired frame size and fps in constructor
			//Camera must be able to support specified framesize and frames per second
			//or this will set camera to defaults
			while(!vcap.open(videoStreamAddress, FRAME_WIDTH, FRAME_HEIGHT, FPS)){
				System.out.println("ERROR connecting to camera stream, retrying " + count);
				count++;
				try {
					Thread.sleep(1000); // Wait for 1 second to retry
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			// After Opening Camera we need to configure the returned image
			// setting
			// all opencv v4l2 camera controls scale from 0.0 - 1.0
			System.out.println("EXPOSURE: " + vcap.get(CAP_PROP_EXPOSURE));
			System.out.println("BRIGHTNESS: " + vcap.get(CAP_PROP_BRIGHTNESS));
			System.out.println("CONTRAST: " + vcap.get(CAP_PROP_CONTRAST));

			vcap.set(CAP_PROP_EXPOSURE, -10.0);
			vcap.set(CAP_PROP_BRIGHTNESS, 30.0);
			vcap.set(CAP_PROP_CONTRAST, 1.0);

			System.out.println("EXPOSURE: " + vcap.get(CAP_PROP_EXPOSURE));
			System.out.println("BRIGHTNESS: " + vcap.get(CAP_PROP_BRIGHTNESS));
			System.out.println("CONTRAST: " + vcap.get(CAP_PROP_CONTRAST));

			System.out.println("Successfully connected to USB Camera!");
			System.out.println();

			// Set global boolean to true
			cameraConnected.set(true);
			
			// Calculate setup time for stream
			end = System.currentTimeMillis();
			System.out.println("It took " + ((start-end)/1000.0) + " seconds to set up stream");
			// Start timing for flushing the buffer
			bufferStart = System.currentTimeMillis();
			
			// Now, run this thread in a continuous loop
			while(true){
				synchronized (rawImgMutex) {
					vcap.read(frame);	// Load the current camera frame into a global variable
				}
				
				// End timer to get time since stream started
				bufferEnd = System.currentTimeMillis();
				double bufferDifference = (bufferEnd - bufferStart)/1000.0;
				
				//The stream takes a while to start up, and because of it, images from the camera
				//buffer. We don't have a way to jump to the end of the stream to get the latest image, so we
				//run this loop as fast as we can and throw away all the old images. This way, we wait some number of seconds
				//before we are at the end of the stream, and can allow processing to begin.
				if((bufferDifference >= BUFFER_FLUSH_DELAY) && !processingImage.get()){
					System.out.println("Buffer Cleared: Startin Processing Thread");
					processingImage.set(true);
				}
				
				try {
					Thread.sleep(5); // Sleep for 5 millis to prevent infinite loop
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class ImageProcessingRunnable implements Runnable {

		@Override
		public void run() {
			// Create local processing variables
			Mat rawImage = new Mat();
			Mat hsv = new Mat();
			Mat binImage = new Mat();
			Mat outputImage = new Mat();
			boolean frameEmpty = true;
			
			// Continuously run loop
			while(true){
				// Check to see whether or not processing is enabled
				if(processingImage.get()){
					synchronized(rawImgMutex){
						if(!frame.empty()){
							// Copy global frame to local thread
							frame.copyTo(rawImage);
						}
						
						frameEmpty = frame.empty(); // Update local variable
					}
					
					// If there was a frame, process it
					if(!frameEmpty){
						processImage(rawImage, hsv, binImage, outputImage);
					}
				}
				
				try {
					Thread.sleep(5); // Sleep for 5 millis to prevent infinite loop
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		private void processImage(Mat rawImage, Mat hsv, Mat binImage, Mat outputImage){
			// Threshold the raw image into a binary image
			thresholdHSV(rawImage, binImage);

			// Find contours and outline convex hull around shapes
			List<SmartContour> contours = new ArrayList<SmartContour>();
			convexHull(binImage, contours);

			// Score the contours based on certain criteria
			scoreContours(rawImage, contours);
		}
		
		public void thresholdHSV(Mat rawRGBImage, Mat binaryDst) {
			// Convert to HSV color space
			Mat hsv = new Mat();
			Imgproc.cvtColor(rawRGBImage, hsv, Imgproc.COLOR_BGR2HSV);

			// Split color channels
			List<Mat> dsts = new ArrayList<Mat>();
			Core.split(hsv, dsts);
			Mat hueSrc = dsts.get(0);
			Mat satSrc = dsts.get(1);
			Mat valSrc = dsts.get(2);

			// Threshold channels individually
			Mat hueDst = new Mat();
			Mat satDst = new Mat();
			Mat valDst = new Mat();
			Mat binTemp = new Mat();

			Imgproc.threshold(hueSrc, hueDst, HUE_MIN, HUE_MAX,
					Imgproc.THRESH_BINARY);
			Imgproc.threshold(satSrc, satDst, SAT_MIN, SAT_MAX,
					Imgproc.THRESH_BINARY);
			Imgproc.threshold(valSrc, valDst, VAL_MIN, VAL_MAX,
					Imgproc.THRESH_BINARY);

			// Combine operations to form binary image (and return it)
			Core.bitwise_and(hueDst, satDst, binTemp);
			Core.bitwise_and(valDst, binTemp, binaryDst);
		}
		
		private void mapHullPoints(List<MatOfPoint> contours,
				List<MatOfInt> hull, List<MatOfPoint> hullMOP) {

			// Convert MatOfInt of convex hull to MatOfPoint of contours

			// Loop over all contours
			List<Point[]> hullpoints = new ArrayList<Point[]>();
			for (int i = 0; i < hull.size(); i++) {
				Point[] points = new Point[hull.get(i).rows()];

				// Loop over all points that need to be hulled in current contour
				for (int j = 0; j < hull.get(i).rows(); j++) {
					int index = (int) hull.get(i).get(j, 0)[0];
					points[j] = new Point(contours.get(i).get(index, 0)[0],
							contours.get(i).get(index, 0)[1]);
				}

				hullpoints.add(points);
			}

			// Convert Point arrays into MatOfPoint
			for (int i = 0; i < hullpoints.size(); i++) {
				MatOfPoint mop = new MatOfPoint();
				mop.fromArray(hullpoints.get(i));
				hullMOP.add(mop);
			}
		}

		private void convexHull(Mat binaryImage, List<SmartContour> contourDst) {
			// Find contours in the image
			List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
			Imgproc.findContours(binaryImage, contours, new Mat(),
					Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

			// Find the convex hull object for each contour
			List<MatOfInt> hull = new ArrayList<MatOfInt>();
			for (int i = 0; i < contours.size(); i++) {
				hull.add(new MatOfInt());
			}
			for (int i = 0; i < contours.size(); i++) {
				Imgproc.convexHull(contours.get(i), hull.get(i));
			}

			// Map convex hull points to contour points (OpenCV Java fix)
			List<MatOfPoint> hullMOP = new ArrayList<MatOfPoint>();
			mapHullPoints(contours, hull, hullMOP);

			// Return contours into SmartContour List
			for (MatOfPoint mop : hullMOP) {
				contourDst.add(new SmartContour(mop));
			}
		}

		private void scoreContours(Mat rawImage, List<SmartContour> contours) {

			// Iterate through contours and score to see if they meet criteria
			// BE CAREFULL WITH MIN AREA (different for 320x240)
			SmartContour cLeft = null, cRight = null;
			for (SmartContour c : contours) {
				if (c.getAspectRatio() > 0.80 && c.getAspectRatio() < 2.0
						&& c.getArea() > 1200) {
					if (c.isLeft()) {
						if (cLeft == null) {
							cLeft = c;
						}
					}
					if (c.isRight()) {
						if (cRight == null) {
							cRight = c;
						}
					}
				}
			}

			Core.line(rawImage, new Point(rawImage.width() / 4, 0), new Point(
					rawImage.width() / 4, rawImage.height()), COLOR_BLUE, 5);

			if (cLeft != null) {
				Core.circle(rawImage, cLeft.getCenter(), 5, COLOR_GREEN, -5);
				Core.rectangle(rawImage, cLeft.getTopLeft(),
						cLeft.getBottomRight(), COLOR_GREEN, 3);
				
				synchronized(measurementMutex){
					leftExists = true;
				}
			} else {
				synchronized (measurementMutex) {
					leftExists = false;
				}
			}

			if (cRight != null) {
				Core.rectangle(rawImage, cRight.getTopLeft(),
						cRight.getBottomRight(), COLOR_YELLOW, 3);
				Core.line(rawImage,
						new Point(rawImage.width() / 4, cRight.getCenter().y),
						cRight.getCenter(), COLOR_RED, 5);
				Core.circle(rawImage, cRight.getCenter(), 5, COLOR_YELLOW, -5);
				
				// Calculate error from center to pass off to PID Loop
				double xOffset = 0;
				double targetX = (rawImage.width() / 4.0) + xOffset;
				double scaledError = (cRight.getCenter().x - targetX) / targetX;
				scaledError = (scaledError > 1.0) ? 1.0 : scaledError;
				scaledError = (scaledError < -1.0) ? -1.0 : scaledError;
				
				synchronized(measurementMutex){
					errorFromRight = scaledError;
					rightExists = true;
				}
			} else {
				synchronized (measurementMutex) {
					errorFromRight = 0;
					rightExists = false;
				}
			}
		}
		
	}
	
}
