package robot; 

public enum RobotEnum {
	
	FRONT_LEFT(3),
	BACK_LEFT(1),
	FRONT_RIGHT(4),
	BACK_RIGHT(2),
	JOYSTICK_PORT(0),
	SPIN_BUTTON(1);
	
	public final int PORT;
	
	RobotEnum(int port) {
		this.PORT = port;
	}
	
}
