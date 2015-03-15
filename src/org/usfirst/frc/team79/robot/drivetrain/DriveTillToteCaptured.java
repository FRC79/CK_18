package org.usfirst.frc.team79.robot.drivetrain;

import org.usfirst.frc.team79.robot.CommandBase;

public class DriveTillToteCaptured extends CommandBase {

	boolean running = false;
	
	public DriveTillToteCaptured() {
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
		running = true;
		setTimeout(1.0); // Shouldn't run longer than a second
	}

	@Override
	protected void execute() {
		if(!drivetrain.isToteCaptured()){
			drivetrain.move(0, 0.25);
		} else {
			running = false;
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return (!running || isTimedOut());
	}

	@Override
	protected void end() {
		drivetrain.stop();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
