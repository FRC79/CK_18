package org.usfirst.frc.team79.robot.totelift;

import org.usfirst.frc.team79.robot.CommandBase;

public class LiftToteToHeightFast extends CommandBase {

	double potValue;

	public LiftToteToHeightFast(double potValue) {
		requires(toteLift);
	
		this.potValue = potValue;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		toteLift.setMotor(ToteLift.LIFT_POWER);
	}

	@Override
	protected boolean isFinished() {
		return (toteLift.getPot() >= potValue);
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
