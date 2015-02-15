package org.usfirst.frc.team79.robot.totelift;

import org.usfirst.frc.team79.robot.CommandBase;
import org.usfirst.frc.team79.robot.util.KUtil;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopLift extends CommandBase {

	public TeleopLift() {
		requires(toteLift);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		// Output potentiometer values to SD
		SmartDashboard.putNumber("LIFT POT", toteLift.getPot());

		// Tote Lift Mechanism
		if (toteLift.atBottom()
				&& KUtil.deadband(-oi.manipGamepad.getRawAxis(3)) < 0) {
			// If we're at the bottom and we want to go down, stop.
			toteLift.setMotor(0);
		} else if (toteLift.atTop()
				&& KUtil.deadband(-oi.manipGamepad.getRawAxis(3)) > 0) {
			// If we're at the top and want to go up, stop.
			toteLift.setMotor(0);
		} else {
			// Move according to the user input.
			toteLift.setMotor(Math.pow(
					KUtil.deadband(-oi.manipGamepad.getRawAxis(3)), 3));
		}
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
