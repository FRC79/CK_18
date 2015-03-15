package org.usfirst.frc.team79.robot.drivetrain;

import org.usfirst.frc.team79.robot.CommandBase;

public class StopDrivetrain extends CommandBase {

	public StopDrivetrain() {
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
		setTimeout(0.004);
	}

	@Override
	protected void execute() {
		drivetrain.stop();
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
