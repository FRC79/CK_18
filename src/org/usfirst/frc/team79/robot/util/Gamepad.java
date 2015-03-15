package org.usfirst.frc.team79.robot.util;

import edu.wpi.first.wpilibj.Joystick;

/*
 * 	Gamepad class that extends a regular joystick.
 */

public class Gamepad extends Joystick {

	public Gamepad(int port) {
		super(port);
	}
	
	// Invert axes to compensate for abnormality
	@Override
	public double getRawAxis(int axis) {
		return -super.getRawAxis(axis);
	}
	
	// Button Mappings
	public static final int X_BUTTON = 1;
	public static final int A_BUTTON = 2;
	public static final int B_BUTTON = 3;
	public static final int Y_BUTTON = 4;
	
	// Joystick Mappings
	public static final int LEFT_X_AXIS = 0;
	public static final int LEFT_Y_AXIS = 1;
	public static final int RIGHT_X_AXIS = 2;
	public static final int RIGHT_Y_AXIS = 3;
			
}
