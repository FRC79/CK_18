package org.usfirst.frc.team79.robot;

import org.usfirst.frc.team79.robot.util.CSVFile;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// Ports and IDs for individual modules
	
	// SPEED CONTROLLERS
	public static int FL_WHEEL_MOTOR_PORT = 2;
	public static int BL_WHEEL_MOTOR_PORT = 0;
	public static int FR_WHEEL_MOTOR_PORT = 3;
	public static int BR_WHEEL_MOTOR_PORT = 1;
	
	public static int TOTE_LIFT_MOTOR_PORT = 4;
	
	public static int CONTAINER_GRIPPER_MOTOR_PORT = 6;
	public static int CONTAINER_ARM_MOTOR_PORT = 5;
	
	// ANALOG INPUTS
	public static int GYRO_PORT = 0;
	public static int SONAR_PORT = 1;
	public static int TOTE_LIFT_POT_PORT = 2;
	
	// DIGITAL INPUTS
	public static int TOTE_LIFT_TOP_SWITCH_PORT = 1;
	public static int TOTE_LIFT_BOTTOM_SWITCH_PORT = 2;
	public static int TOTE_CAPTURED_SWITCH_PORT = 4;
	
	public static int CONTAINER_GRIPPER_OPEN_SWITCH_PORT = 5;
	public static int CONTAINER_GRIPPER_CLOSED_SWITCH_PORT = 6;
	public static int CONATINER_ARM_TOP_SWITCH_PORT = 3;
	public static int CONTAINER_ARM_BOTTOM_SWITCH_PORT = 0;
	
	// JOYSTICKS
	public static int DRIVER_JOYSTICK_ID = 0;
	public static int MANIP_GAMEPAD_ID = 1;
	
	public static void init(){
	}
	
	// Load configuration settings from CSVs
	public static void loadCSVSettings(){
		
	}
}
