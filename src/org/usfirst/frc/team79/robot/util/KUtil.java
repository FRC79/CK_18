package org.usfirst.frc.team79.robot.util;

public class KUtil {

	public static final double DEADBAND_TOLERANCE = 0.05;
	
	public static double deadband(double joystickval) {
		return deadband(joystickval, DEADBAND_TOLERANCE);
	}
	
	public static double deadband(double joystickval, double tolerance){
		return (Math.abs(joystickval) > tolerance) ? joystickval : 0.0;
	}
	
	public static boolean withinTolerance(double input, double target, double tolerance){
		return (Math.abs(target - input) <= tolerance);
	}
}
