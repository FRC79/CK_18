
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
	
	Gyro gyro;
	
	RobotDrive robotDrive;
	Joystick moveStick;
	
	private double getGyroAngle(){
		return gyro.getAngle() * GYRO_CONVERSION;
	}
	
	public void robotInit() {
		gyro = new Gyro(0);
		gyro.initGyro();	// Run calibration (most likely 5 seconds halting)
		
    	int FL = 0;
    	int FR = 1;
    	int BL = 3;
    	int BR = 2;
    	
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
    	gyro.reset();	// Reset heading to 0 degs
    }
    
    boolean userNowRotating = false;
    boolean userWasRotating = false;
    
    public void teleopPeriodic() {
    	// Update current state variable (Is the user rotating?)
    	userNowRotating = (Math.abs(moveStick.getRawAxis(3)) > 0.05);
    	
    	// Performs gyro-stabilization when translating to eliminate drift
    	double rotVal = 0.0;
    	if(userNowRotating){
    		// Rotate at user input power
    		rotVal = moveStick.getRawAxis(3);
    	} else {
    		if(userWasRotating){
    			// Reset gyro heading to 0 (the new direction the robot is translating in)
    			gyro.reset();
    		} else {
    			// Stabilize heading to 0
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
    }
    
}
