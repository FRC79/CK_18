package org.usfirst.frc.team79.robot.totelift;

import org.usfirst.frc.team79.robot.CommandBase;

public class LiftToteToHeightFast extends CommandBase {

	double potValue;

	double LIFT_POWER = 1.0;
	double LOWER_POWER = -0.25;
	double SLOW_LOWER_POWER = -0.12;
	double SLOW_LIFT_POWER = 0.5;
	
	public LiftToteToHeightFast(double potValue) {
		requires(toteLift);
	
		this.potValue = potValue;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		toteLift.setMotor(LIFT_POWER);
	}

	@Override
	protected boolean isFinished() {
		return (toteLift.getPot() > potValue);
	}

	@Override
	protected void end() {
		toteLift.setMotor(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
