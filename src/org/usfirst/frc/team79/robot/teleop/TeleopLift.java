package org.usfirst.frc.team79.robot.teleop;

import org.usfirst.frc.team79.robot.CommandBase;
import org.usfirst.frc.team79.robot.totelift.ToteLift;
import org.usfirst.frc.team79.robot.util.KUtil;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopLift extends CommandBase {

	double lastPot = 0;
	
	public TeleopLift() {
		requires(toteLift);
	}

	@Override
	protected void initialize() {
		lastPot = toteLift.getPot();
	}

	@Override
	protected void execute() {
		
		SmartDashboard.putNumber("POT", toteLift.getPot());
		
		// Tote Lift Mechanism
		if (toteLift.getPot() >= ToteLift.TOP_POT_STOP
				&& KUtil.deadband(OI.manipGamepad.getRawAxis(3)) > 0) {
			// If we're at the top and want to go up, stop.
			toteLift.setMotor(0);
		} else {
			if(OI.manipGamepad.getRawButton(2)){
				if(toteLift.getPot() <= ToteLift.BOTTOM_POT_SLOWER_STOP){
					toteLift.setMotor(ToteLift.SLOW_LOWER_POWER);
				} else {
					toteLift.setMotor(ToteLift.SNAP_DOWN_POWER);
				}
			} else if(OI.manipGamepad.getRawButton(3)) {
				if(toteLift.getPot() >= ToteLift.TOP_POT_STOP){
					toteLift.setMotor(0);
				} else {
					toteLift.setMotor(ToteLift.LIFT_POWER);
				}
			} else {
				// If we want to move up or down and we aren't hitting a limit
				if (Math.abs(OI.manipGamepad.getRawAxis(3)) > KUtil.DEADBAND_TOLERANCE) {
					// Drive continuously at set speed
					double directionCoeff = OI.manipGamepad.getRawAxis(3)
							/ Math.abs(OI.manipGamepad.getRawAxis(3));
					
					if(directionCoeff < 0){
						
						if(toteLift.getPot() > ToteLift.BOTTOM_POT_SLOWER_STOP){
							// Down
							toteLift.setMotor(ToteLift.LOWER_POWER);
						} else {
							toteLift.setMotor(ToteLift.SLOW_LOWER_POWER);
						}
						
					} else if(directionCoeff > 0){
						// Up
						toteLift.setMotor(ToteLift.SLOW_LIFT_POWER);
					}
				} else {
					// Stop
					toteLift.setMotor(0.0);
				}
			}
		}
		
		lastPot = toteLift.getPot();
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
