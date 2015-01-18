package krunch18.drivetrain;

import krunch18.RobotMap;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
	public static final double kP = 0.03;	// Proportional constant for gyro feedback loop
	public static final double GYRO_CONVERSION = 0.535714289; // Calculated experimentally
	
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
		
		accel = new BuiltInAccelerometer(Range.k4G);
		
		gyro = new Gyro(RobotMap.GYRO_PORT);
		gyro.initGyro(); // Gyro calibration mode (keep robot still, takes a few seconds)
	}
	
	
	
	// Cartesian Mecanum
	public void driveXY(double powerX, double powerY){
		driveXY(powerX, powerY, 0);
	}
	
	// Cartesian Mecanum with rotation
	public void driveXY(double powerX, double powerY, double powerRot){
		robotDrive.mecanumDrive_Cartesian(powerX, powerY, powerRot, 0);
	}
	
	// Cartesian mecanum with auto-stabilization
	public void driveXY_AS(double powerX, double powerY){
		driveXY(powerX, powerY, -getGyro()*kP);
	}
	
	
	
	public double getGyro() {
		return gyro.getAngle() * GYRO_CONVERSION;
	}
	
	public void resetGyro(){
		gyro.reset();
	}
	
    public void initDefaultCommand() {
    }
}

