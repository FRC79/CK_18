package org.usfirst.frc.team79.robot.totelift;

import org.usfirst.frc.team79.robot.CommandBase;

public class LowerLiftFast extends CommandBase {

	double potStop = 2000;
	
	double LIFT_POWER = 1.0;
	double LOWER_POWER = -0.25;
	double SLOW_LOWER_POWER = -0.12;
	double SLOW_LIFT_POWER = 0.5;
	
	double snapPower = -0.5;
	
	public LowerLiftFast() {
		requires(toteLift);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		if(toteLift.getPot() < potStop){
			toteLift.setMotor(SLOW_LOWER_POWER);
		} else {
			toteLift.setMotor(snapPower);
		}
	}

	@Override
	protected boolean isFinished() {
		return (toteLift.getPot() <= ToteLift.BOTTOM_POT_STOP); // At bottom
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		toteLift.setMotor(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
