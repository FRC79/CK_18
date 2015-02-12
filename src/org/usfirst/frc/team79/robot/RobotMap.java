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
	
	//Gripper Motors
	public static int GRIPPER_MOTOR = 6;
	public static int GRIPPER_ARM_MOTOR = 5;
	
	// ANALOG INPUTS
	public static int GYRO_PORT = 0;
	
	// POTENTIOMETER
	public static int POTENTIOMETER = 2;
	
	// JOYSTICKS
	public static int MOVE_STICK_ID = 0;
	public static int ROT_STICK_ID = 1;
	public static int CONTROL_STICK_ID = 2;
	
	//Gamepad RawAxis
	public static int LIFT_RAW_VALUE = 3;
	public static int GRIPPER_ARM_RAW_VALUE = 1;
	public static int GRIPPER_RAW_VALUE = 0;
	
	// Limit Switches
	public static int LIFT_TOP_LIMIT_ID = 3;
	public static int LIFT_BOTTOM_LIMIT_ID = 2;
	
	public static int TOTE_ENGGED_ID = 4;
	
	public static int GRIP_OPEN_LIMIT_ID = 5;
	public static int GRIP_CLOSE_LIMIT_ID = 6;
	
	public static int ACTUATOR_TOP_ID = 1;
	public static int ACTUAT0R_BOTTOM_ID = 0;
	
	//Controller Buttons
	public static int ELEVATOR_DOWN = 5;
	public static int ELEVATOR_UP = 6;
	public static int GRIPPER_ARM_UP = 2;
	public static int GRIPPER_ARM_DOWN = 3;
	
	// Load configuration settings from CSVs
	public static void loadCSVSettings(){
		
	}
}
