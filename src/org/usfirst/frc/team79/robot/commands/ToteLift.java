package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.CommandBase;

public class ToteLift extends CommandBase {
	
	public double speed;
	
	public ToteLift(double speed) {
		requires(totelifter);
		this.speed = speed;
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		totelifter.move(speed);
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
