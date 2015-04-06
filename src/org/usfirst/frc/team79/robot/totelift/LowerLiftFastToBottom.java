package org.usfirst.frc.team79.robot.totelift;

import org.usfirst.frc.team79.robot.CommandBase;

public class LowerLiftFastToBottom extends CommandBase {

	public LowerLiftFastToBottom() {
		requires(toteLift);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		if(toteLift.getPot() < ToteLift.BOTTOM_POT_SLOWER_STOP){
			toteLift.setMotor(ToteLift.SLOW_LOWER_POWER);
		} else {
			toteLift.setMotor(ToteLift.SNAP_DOWN_POWER);
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
