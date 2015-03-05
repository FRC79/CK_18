package org.usfirst.frc.team79.robot.teleop;

import org.usfirst.frc.team79.robot.RobotMap;
import org.usfirst.frc.team79.robot.util.Gamepad;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public static Joystick driverJoystick;
    public static Gamepad manipGamepad;
    public static JoystickButton raiseContainerButton, lowerContainerButton;
    
    public static void init(){
    	// Init joysticks
    	driverJoystick = new Joystick(RobotMap.DRIVER_JOYSTICK_ID);
    	manipGamepad = new Gamepad(RobotMap.MANIP_GAMEPAD_ID);
    	
    	// Init joystick buttons
    	
    	// Define what the buttons do
    }
    
    public static double getDriveRot() {
		return driverJoystick.getRawAxis(3);
	}
}

