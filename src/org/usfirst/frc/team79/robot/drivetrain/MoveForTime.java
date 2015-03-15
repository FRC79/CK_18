package org.usfirst.frc.team79.robot.drivetrain;

import org.usfirst.frc.team79.robot.CommandBase;

public class MoveForTime extends CommandBase {

	double duration = 0.0;
	double powerX = 0.0, powerY = 0.0;
	boolean usesGyro = false;

	public MoveForTime(double powerX, double powerY, double duration, boolean usesGyro) {
		requires(drivetrain);
		
		this.duration = duration;
		this.powerX = powerX;
		this.powerY = -powerY;
		this.usesGyro = usesGyro;
	}

	@Override
	protected void initialize() {
		setTimeout(duration);
		
		if(usesGyro){
			drivetrain.resetGyro();
		}
	}

	@Override
	protected void execute() {
		if(usesGyro){
			drivetrain.moveStraightly(powerX, powerY);
		} else {
			drivetrain.move(powerX, powerY); // Drive forward
		}
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
