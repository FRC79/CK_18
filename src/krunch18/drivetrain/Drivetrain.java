package robot.driveTrain;

import robot.RobotEnum;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	
	RobotDrive robotDrive;
	
	public DriveTrain() {
		robotDrive = new RobotDrive(RobotEnum.FRONT_LEFT.PORT, RobotEnum.BACK_LEFT.PORT, RobotEnum.FRONT_RIGHT.PORT, RobotEnum.BACK_RIGHT.PORT);
	}
	
	public void arcadeDrive(Joystick joystick) {
		robotDrive.arcadeDrive(joystick);
	}
	
	public void move(double leftOutput, double rightOutput) {
		robotDrive.setLeftRightMotorOutputs(leftOutput, rightOutput);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(null);
	}
	
}
