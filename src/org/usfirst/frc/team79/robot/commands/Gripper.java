package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.CommandBase;

public class Gripper extends CommandBase {
	
	public double speed;

	public Gripper(double speed) {
		requires(binlifter);
		this.speed = speed;
	}
	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		binlifter.grip(speed);
	}

	@Override
	protected boolean isFinished() {
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
