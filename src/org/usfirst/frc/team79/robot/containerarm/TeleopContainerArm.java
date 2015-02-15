package org.usfirst.frc.team79.robot.containerarm;

import org.usfirst.frc.team79.robot.CommandBase;
import org.usfirst.frc.team79.robot.util.KUtil;

public class TeleopContainerArm extends CommandBase {

	double LIFT_POWER = 0.25;
	
	public TeleopContainerArm() {
		requires(containerArm);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {

		// Container Arm lifting mechanism
		if (containerArm.atBottom()
				&& KUtil.deadband(-oi.manipGamepad.getRawAxis(1)) < 0) {
			// If we're at the bottom, and we want to go down, stop.
			containerArm.setLiftMotor(0);
		} else if (containerArm.atTop()
				&& KUtil.deadband(-oi.manipGamepad.getRawAxis(1)) > 0) {
			// If we're at the top, and want to go up, stop.
			containerArm.setLiftMotor(0);
		} else {
			// If we want to move up or down and we aren't hitting a limit
			if (Math.abs(-oi.manipGamepad.getRawAxis(1)) > KUtil.DEADBAND_TOLERANCE) {
				// Drive continuously at set speed
				double directionCoeff = -oi.manipGamepad.getRawAxis(1)
						/ Math.abs(-oi.manipGamepad.getRawAxis(1));
				containerArm.setLiftMotor(LIFT_POWER * directionCoeff);
			} else {
				// Stop
				containerArm.setLiftMotor(0.0);
			}
		}

		
		// Container gripper open/close mechanism
		if (oi.manipGamepad.getRawButton(3)
				&& !containerArm.isGripperCompletelyOpen()) {
			// Keep opening the gripper
			containerArm.setGripperMotor(1.0);
		} else if (oi.manipGamepad.getRawButton(2)
				&& !containerArm.isGripperClosed()) {
			// Keep closing the gripper
			containerArm.setGripperMotor(-1.0);
		} else {
			// Stop
			containerArm.setGripperMotor(0);
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
