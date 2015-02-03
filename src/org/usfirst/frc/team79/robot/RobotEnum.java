package org.usfirst.frc.team79.robot;

public enum RobotEnum {
	
	FRONT_LEFT(3),
	BACK_LEFT(1),
	FRONT_RIGHT(4),
	BACK_RIGHT(2),
	LIFT_SYSTEM_MOTOR(5),
	
	JOYSTICK_PORT(0),
	MANIPULATOR_STICK_ID(3);
	
	public final int PORT;
	
	RobotEnum(int port) {
		this.PORT = port;
	}
	
}
