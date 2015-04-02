package org.usfirst.frc.team79.robot.teleop;

import org.usfirst.frc.team79.robot.CommandBase;
import org.usfirst.frc.team79.robot.util.KUtil;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopLift extends CommandBase {

	double LIFT_POWER = 1.0;
	double LOWER_POWER = -0.25;
	double SLOW_LOWER_POWER = -0.12;
	double SLOW_LIFT_POWER = 0.5;
	
	double potSpotTop = 3320;
	double potSpotBottom = 2000;
	double snapPower = -0.50;
	
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
		if (toteLift.atBottom()
				&& KUtil.deadband(OI.manipGamepad.getRawAxis(3)) < 0) {
			// If we're at the bottom and we want to go down, stop.
			toteLift.setMotor(0);
		} else if ((toteLift.atTop() || toteLift.getPot() >= potSpotTop)
				&& KUtil.deadband(OI.manipGamepad.getRawAxis(3)) > 0) {
			// If we're at the top and want to go up, stop.
			toteLift.setMotor(0);
		} else {
			if(OI.manipGamepad.getRawButton(2)){
				if(toteLift.getPot() <= potSpotBottom){
					if(toteLift.atBottom()){
						toteLift.setMotor(0);
					} else {
						toteLift.setMotor(SLOW_LOWER_POWER);
					}
				} else {
					toteLift.setMotor(snapPower);
				}
			} else if(OI.manipGamepad.getRawButton(3)) {
				if(toteLift.atTop() || toteLift.getPot() >= potSpotTop){
					toteLift.setMotor(0);
				} else {
					toteLift.setMotor(LIFT_POWER);
				}
			} else {
				// If we want to move up or down and we aren't hitting a limit
				if (Math.abs(OI.manipGamepad.getRawAxis(3)) > KUtil.DEADBAND_TOLERANCE) {
					// Drive continuously at set speed
					double directionCoeff = OI.manipGamepad.getRawAxis(3)
							/ Math.abs(OI.manipGamepad.getRawAxis(3));
					
					if(directionCoeff < 0){
						
						if(toteLift.getPot() > potSpotBottom){
							// Down
							toteLift.setMotor(LOWER_POWER);
						} else {
							toteLift.setMotor(SLOW_LOWER_POWER);
						}
						
					} else if(directionCoeff > 0){
						// Up
						toteLift.setMotor(SLOW_LIFT_POWER);
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
