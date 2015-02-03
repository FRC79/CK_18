package org.usfirst.frc.team79.subsystems;

import org.usfirst.frc.team79.robot.CommandBase;
import org.usfirst.frc.team79.robot.RobotEnum;

import edu.wpi.first.wpilibj.Joystick;

public class ManualController extends CommandBase {
	
	Joystick joystick;
	
	public ManualController() {
		requires(toteManipulator);
		joystick = new Joystick(RobotEnum.MANIPULATOR_STICK_ID.PORT);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		toteManipulator.moveArm(joystick.getY());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}

}
