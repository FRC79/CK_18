package org.usfirst.frc.team79.robot.drivetrain;

import org.usfirst.frc.team79.robot.CommandBase;

public class MoveForTime extends CommandBase {

	double duration = 0.0;
	double powerX = 0.0, powerY = 0.0;
	
	public MoveForTime(double powerX, double powerY, double duration) {
		requires(drivetrain);
		
		this.duration = duration;
		this.powerX = powerX;
		this.powerY = -powerY;
	}
	
	@Override
	protected void initialize() {
		setTimeout(duration);
	}

	@Override
	protected void execute() {
		drivetrain.move(powerX, powerY); // Drive forward
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isTimedOut();
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		drivetrain.stop();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
