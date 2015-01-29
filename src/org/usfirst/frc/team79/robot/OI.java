package org.usfirst.frc.team79.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public Joystick moveStick, rotStick;
    
    public static int MODE_DUAL_JOYSTICKS = 0;
    public static int MODE_SINGLE_JOYSTICK = 1;
    
    public OI(){
    	moveStick = new Joystick(RobotMap.MOVE_STICK_ID);
    	// Init rot stick in teleop
    }
}

