package robot.commands;

import robot.CommandBase;

public class Spin extends CommandBase {
	
	public Spin(double duration) {
		requires(drive);
		setTimeout(duration);
	}
	
	public Spin() {
		requires(drive);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		drive.move(0.5, -0.5);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
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
