package org.usfirst.frc.team79.robot.containerarm;

import org.usfirst.frc.team79.robot.CommandBase;

public class StopContainerArm extends CommandBase {

	public StopContainerArm() {
		requires(containerArm);
	}
	
	@Override
	protected void initialize() {
		setTimeout(0.004);
	}

	@Override
	protected void execute() {
		containerArm.stop();
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
