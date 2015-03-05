package org.usfirst.frc.team79.robot.drivetrain;

import org.usfirst.frc.team79.robot.RobotMap;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	Drivetrain subsystem
 */
public class Drivetrain extends Subsystem {
    
	public static final double kP = 0.025;	// Proportional constant for gyro feedback loop
	public static final double GYRO_CONVERSION = 0.535714289; // Calculated experimentally
	public static final double POWER_PERCENTAGE = 1.0;		// Ratio if input motor value and output motor value
	
	public RobotDrive robotDrive;
	public Gyro gyro;
	
	public Drivetrain(){
		// Init components
		robotDrive = new RobotDrive(
				RobotMap.FL_WHEEL_MOTOR_PORT, 
				RobotMap.BL_WHEEL_MOTOR_PORT, 
				RobotMap.FR_WHEEL_MOTOR_PORT, 
				RobotMap.BR_WHEEL_MOTOR_PORT
		);
		robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
		robotDrive.setInvertedMotor(MotorType.kRearRight, true);
		
		gyro = new Gyro(RobotMap.GYRO_PORT);
		gyro.initGyro(); // Gyro calibration mode (keep robot still, takes a few seconds)
	}
	
	
	
	// Drives with motor outputs in the x and y directions with no rotation
	public void move(double powerX, double powerY){
		move(powerX, powerY, 0);
	}
	
	// Drives with motor outputs in the x, y, and yaw directions
	public void move(double powerX, double powerY, double powerRot){
		robotDrive.mecanumDrive_Cartesian(powerX * POWER_PERCENTAGE, powerY * POWER_PERCENTAGE, powerRot * POWER_PERCENTAGE, 0);
	}
	
	// Drives with motor outputs in the x and y 
	// directions while maintaining orientation with the gyro.
	public void moveStraightly(double powerX, double powerY){
		move(powerX, powerY, -getGyro()*kP);
	}
	
	public void stop(){
		move(0, 0);
	}

	
	public double getGyro() {
		return gyro.getAngle() * GYRO_CONVERSION;
	}
	
	public void resetGyro(){
		gyro.reset();
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new StopDrivetrain());
    }
}

