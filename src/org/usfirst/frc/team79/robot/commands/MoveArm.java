package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.CommandBase;

/*
*Constructor takes a motor percentage and a timeout duration
*/
public class LiftArm extends CommandBase {
	
	public double speed;
	/*
	*Takes a motor percentage and a timeout duration
	*/
	public LiftArm(double getSpeed, double timeout) {
		requires(toteManipulator);
		setTimeout(timeout);
		speed = getSpeed;
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		toteManipulator.moveArm(speed);
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
