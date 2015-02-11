package org.usfirst.frc.team79.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
    public Joystick moveStick, rotStick;
    public Joystick controlPad;
    public JoystickButton gripperOpen, gripperClose, gripperArmUp, gripperArmDown, toteLiftUp, toteLiftDown; 
    
    public static int MODE_DUAL_JOYSTICKS = 0;
    public static int MODE_SINGLE_JOYSTICK = 1;
    
    public OI(){
    	moveStick = new Joystick(RobotMap.MOVE_STICK_ID);
    	controlPad = new Joystick(RobotMap.MANIPULATOR_STICK_ID);

    	gripperArmDown = new JoystickButton(controlPad, RobotMap.GRIPPER_ARM_DOWN);
    	gripperArmUp = new JoystickButton(controlPad, RobotMap.GRIPPER_ARM_UP);
    	
    	toteLiftUp = new JoystickButton(controlPad, RobotMap.ELEVATOR_UP);
    	toteLiftDown = new JoystickButton(controlPad, RobotMap.ELEVATOR_DOWN);
    }
}

