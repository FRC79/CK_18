package org.usfirst.frc.team79.robot.commands;

import robot.CommandBase;

public class DriveForward extends CommandBase {
	
	public DriveForward(double duration) {
		requires(drive);
		setTimeout(duration);
	}
	
	public DriveForward() {
		requires(drive);
	}
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		drive.move(0.5, 0.5);
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
