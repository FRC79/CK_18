package org.usfirst.frc.team79.robot;

import org.usfirst.frc.team79.robot.commands.LiftArm;
import org.usfirst.frc.team79.robot.commands.LowerArm;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public Joystick moveStick, rotStick;
    public Joystick liftStick;
    public JoystickButton lift, lower;
    
    public static int MODE_DUAL_JOYSTICKS = 0;
    public static int MODE_SINGLE_JOYSTICK = 1;
    
    public OI(){
    	moveStick = new Joystick(RobotMap.MOVE_STICK_ID);
    	liftStick = new Joystick(RobotMap.MANIPULATOR_STICK_ID);
    	
    	lift = new JoystickButton(liftStick, 1);
    	lower = new JoystickButton(liftStick, 2);
    	
    	lift.whenPressed(new LiftArm(0.5, 6.0));
    	lower.whileActive(new LowerArm(0.5, 6.0));
    }
}

