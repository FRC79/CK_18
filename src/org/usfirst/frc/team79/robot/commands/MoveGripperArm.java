package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.CommandBase;

public class MoveGripperArm extends CommandBase {
	
	public double speed;
	
	public MoveGripperArm(double speed) {
		requires(binlifter);
		this.speed = speed;
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		binlifter.armPosition(speed);
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
