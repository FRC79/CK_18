package org.usfirst.team79.robot.gripper;

import org.usfirst.frc.team79.robot.CommandBase;

public class OpenGripper extends CommandBase {

	public OpenGripper() {
		requires(gripper);
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		gripper.setMotor(Gripper.OPEN_POWER);
	}

	@Override
	protected boolean isFinished() {
		return gripper.isCompletelyOpen();
	}

	@Override
	protected void end() {
		gripper.stop();
	}

	@Override
	protected void interrupted() {
		
	}

}
