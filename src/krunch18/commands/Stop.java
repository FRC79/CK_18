package robot.commands;

import robot.CommandBase;

public class Stop extends CommandBase {
	
	public Stop() {
		requires(drive);
	}

	@Override
	protected void execute() {
		drive.move(0.0, 0.0);
	}

	@Override
	protected void initialize() {
		
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
