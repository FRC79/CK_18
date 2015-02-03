package org.usfirst.frc.team79.robot;

import edu.wpi.first.wpilibj.Joystick;


public class OI {
	
	public Joystick moveStick;
    	public Joystick liftStick;
	
	public OI() {
		moveStick = new Joystick(RobotEnum.JOYSTICK_PORT.PORT);
    	liftStick = new Joystick(RobotEnum.MANIPULATOR_STICK_ID.PORT);
	}
}

