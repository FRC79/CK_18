package org.usfirst.frc.team79.robot.drivetrain;

import org.usfirst.frc.team79.robot.RobotMap;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
	public static final double kP = 0.025;	// Proportional constant for gyro feedback loop
	public static final double GYRO_CONVERSION = 0.535714289; // Calculated experimentally
	public static final double POWER_PERCENTAGE = 0.75;
	
	public RobotDrive robotDrive;
	public Gyro gyro;
	public BuiltInAccelerometer accel;
	
	public Drivetrain(){
		// Init components
		robotDrive = new RobotDrive(
				RobotMap.FL_WHEEL_PORT, 
				RobotMap.BL_WHEEL_PORT, 
				RobotMap.FR_WHEEL_PORT, 
				RobotMap.BR_WHEEL_PORT
		);
		robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
		robotDrive.setInvertedMotor(MotorType.kRearRight, true);
		
		accel = new BuiltInAccelerometer(Range.k4G);
		
		gyro = new Gyro(RobotMap.GYRO_PORT);
		gyro.initGyro(); // Gyro calibration mode (keep robot still, takes a few seconds)
	}
	
	
	
	// Cartesian Mecanum
	public void moveXY(double powerX, double powerY){
		moveXY(powerX, powerY, 0);
	}
	
	// Cartesian Mecanum with rotation
	public void moveXY(double powerX, double powerY, double powerRot){
		robotDrive.mecanumDrive_Cartesian(powerX * POWER_PERCENTAGE, powerY * POWER_PERCENTAGE, powerRot * POWER_PERCENTAGE, 0);
	}
	
	// Cartesian mecanum with auto-stabilization
	public void moveXY_AS(double powerX, double powerY){
		moveXY(powerX, powerY, -getGyro()*kP);
	}
	
	public void stop(){
		moveXY(0,0);
	}
	
	
	
	public double getGyro() {
		return gyro.getAngle() * GYRO_CONVERSION;
	}
	
	public void resetGyro(){
		gyro.reset();
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new StopDriveMotors(true));
    }
}

