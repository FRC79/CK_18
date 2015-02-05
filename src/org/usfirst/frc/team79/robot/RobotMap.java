package org.usfirst.frc.team79.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// Ports and IDs for individual modules
	
	// SPEED CONTROLLERS
	public static int FL_WHEEL_PORT = 2;
	public static int BL_WHEEL_PORT = 0;
	public static int FR_WHEEL_PORT = 3;
	public static int BR_WHEEL_PORT = 1;
	
	//Lift Motors
	public static int LIFT_SYSTEM_MOTOR = 4;
	
	// ANALOG INPUTS
	public static int GYRO_PORT = 0;
	
	// JOYSTICKS
	public static int MOVE_STICK_ID = 0;
	public static int ROT_STICK_ID = 1;
	public static int MANIPULATOR_STICK_ID = 2;
	
	// Limit Switches
	public static int TOP_LIMIT_ID = 0;
	public static int BOTTOM_LIMIT_ID = 1;
	public static int TOTE_LIMIT_ID = 2;
	
	// Load configuration settings from CSVs
	public static void loadCSVSettings(){
		
	}
}