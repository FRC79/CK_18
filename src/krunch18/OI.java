package krunch18;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public Joystick moveStick, rotStick;
    
    public OI(){
    	moveStick = new Joystick(RobotMap.MOVE_STICK_ID);
    	rotStick = new Joystick(RobotMap.ROT_STICK_ID);
    }
}

