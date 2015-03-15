package org.usfirst.frc.team79.robot.gripper;

import org.usfirst.frc.team79.robot.CommandBase;

public class StopGripper extends CommandBase {

	public StopGripper() {
		requires(gripper);
	}
	
	@Override
	protected void initialize() {
		setTimeout(0.004);
	}

	@Override
	protected void execute() {
		gripper.stop();
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
