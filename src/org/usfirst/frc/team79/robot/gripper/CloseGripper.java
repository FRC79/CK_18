package org.usfirst.frc.team79.robot.gripper;

import org.usfirst.frc.team79.robot.CommandBase;

public class CloseGripper extends CommandBase {

	public CloseGripper() {
		requires(gripper);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		gripper.setMotor(Gripper.CLOSE_POWER);
	}

	@Override
	protected boolean isFinished() {
		return gripper.isClosed();
	}

	@Override
	protected void end() {
		gripper.stop();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
