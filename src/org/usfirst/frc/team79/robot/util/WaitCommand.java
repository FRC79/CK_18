package org.usfirst.frc.team79.robot.util;

import edu.wpi.first.wpilibj.command.Command;

public class WaitCommand extends Command {

	double duration = 0;
	
	public WaitCommand(double duration) {
		this.duration = duration;
	}
	
	@Override
	protected void initialize() {
		setTimeout(duration);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
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
