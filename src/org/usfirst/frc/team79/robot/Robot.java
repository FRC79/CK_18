
package org.usfirst.frc.team79.robot;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	static final double kP = 0.03;	// Proportional constant for gyro feedback loop
	static final double GYRO_CONVERSION = 0.535714289; // Calculated experimentally
	static final double JOYSTICK_DEADBAND = 0.05;
	static final double ANGLE_DELTA_TOLERANCE = 0.05;
	
	boolean userNowRotating, userWasRotating, changeInTheta, stoppingRotation; // Gyro-stabilization states
	double lastGyroVal;
	
	Gyro gyro;
	
	RobotDrive robotDrive;
	Joystick moveStick;
	
	private double getGyroAngle(){
		return gyro.getAngle() * GYRO_CONVERSION;
	}
	
	public void robotInit() {
		gyro = new Gyro(0);
		gyro.initGyro();	// Run calibration (most likely 5 seconds halting)
		
    	int FL = 2;
    	int FR = 0;
    	int BL = 3;
    	int BR = 1;
    	
    	robotDrive = new RobotDrive(FL, BL, FR, BR);
    	robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
    	robotDrive.setInvertedMotor(MotorType.kRearRight, true);
    	
    	moveStick = new Joystick(0);
    }
	
	public void autonomousInit(){
	}
	
    public void autonomousPeriodic() {
    }

    public void teleopInit(){
    	userNowRotating = false;
    	userWasRotating = false;
    	changeInTheta = false;
    	stoppingRotation = false;
    	lastGyroVal = 0.0;
    	
    	gyro.reset();	// Reset heading to 0 degs
    }
    
    public void teleopPeriodic() {
    	// Update current state variable (Is the user rotating?)
    	userNowRotating = (Math.abs(moveStick.getRawAxis(3)) > JOYSTICK_DEADBAND);
    	changeInTheta = (Math.abs(getGyroAngle() - lastGyroVal) > ANGLE_DELTA_TOLERANCE);
    	
    	// Performs gyro-stabilization when translating to eliminate drift
    	double rotVal = 0.0;
    	if(userNowRotating){
    		// Rotate at user input power
    		rotVal = moveStick.getRawAxis(3);
    	} else {
    		if(userWasRotating){
    			// Begin to decelerate the angular rotation
    			stoppingRotation = true;
    		}
    		
    		// Continue to set rotation to 0 unless robot has finally stopped rotating
    		if(stoppingRotation){
    			if(!changeInTheta){
    				// Robot has stopped rotating, so now reset the heading
    				gyro.reset();
    				gyro.reset();
    				gyro.reset();
    				gyro.reset();
    				gyro.reset();
    				stoppingRotation = false;
    			}
    		} else {
    			// Robot should not be rotating, therefore perform auto-stabilization on the heading
    			rotVal = -getGyroAngle()*kP;
    		}
    	}
    	
    	// Maps 3-axis joystick to mecanum drive (X, Y, Rotation)
    	// Last parameter is for the gyro angle for field oriented driving (must supply updated angle for every call)
    	robotDrive.mecanumDrive_Cartesian(moveStick.getX(), moveStick.getY(), rotVal, 0);

    	// Outputs gyro value on SmartDashboard
    	SmartDashboard.putNumber("GYRO", getGyroAngle());
    
    	// Update previous state variable (Was the user just rotating?)
    	userWasRotating = userNowRotating;
    	lastGyroVal = getGyroAngle();
    }
    
}
