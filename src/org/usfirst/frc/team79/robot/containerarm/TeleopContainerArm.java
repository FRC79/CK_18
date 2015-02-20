package org.usfirst.frc.team79.robot.containerarm;

import org.usfirst.frc.team79.robot.CommandBase;
import org.usfirst.frc.team79.robot.util.KUtil;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopContainerArm extends CommandBase {

	double SLOW_LIFT_POWER = 0.25;
	double LIFT_POWER = 0.6;
	double LOWER_POWER = -0.5;
	
	public TeleopContainerArm() {
		requires(containerArm);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {

		// Container Arm lifting mechanism
//		if (containerArm.atBottom()
//				&& KUtil.deadband(-oi.manipGamepad.getRawAxis(1)) < 0) {
//			// If we're at the bottom, and we want to go down, stop.
//			containerArm.setLiftMotor(0);
//		} else if (containerArm.atTop()
//				&& KUtil.deadband(-oi.manipGamepad.getRawAxis(1)) > 0) {
//			// If we're at the top, and want to go up, stop.
//			containerArm.setLiftMotor(0);
//		} else {
			if(oi.manipGamepad.getRawButton(4)){
//				if(containerArm.atTop()){
//					containerArm.setLiftMotor(0);
//				} else {
					containerArm.setLiftMotor(LIFT_POWER);
//				}
			} else if(oi.manipGamepad.getRawButton(1)){
				containerArm.setLiftMotor(LOWER_POWER);
			} else {
				// If we want to move up or down and we aren't hitting a limit
				if (Math.abs(-oi.manipGamepad.getRawAxis(1)) > KUtil.DEADBAND_TOLERANCE) {
					// Drive continuously at set speed
					double directionCoeff = -oi.manipGamepad.getRawAxis(1)
							/ Math.abs(-oi.manipGamepad.getRawAxis(1));
					containerArm.setLiftMotor(SLOW_LIFT_POWER * directionCoeff);
				} else {
					// Stop
					containerArm.setLiftMotor(0.0);
				}
			}
//		}

		SmartDashboard.putBoolean("OPEN", containerArm.isGripperCompletelyOpen());
		SmartDashboard.putBoolean("CLOSED", containerArm.isGripperClosed());
		
		// Container gripper open/close mechanism
		if (KUtil.deadband(oi.manipGamepad.getRawAxis(0)) < 0
				&& !containerArm.isGripperCompletelyOpen()) {
			// Keep opening the gripper
			containerArm.setGripperMotor(-1.0);
		} else if (KUtil.deadband(oi.manipGamepad.getRawAxis(0)) > 0
				&& !containerArm.isGripperClosed()) {
			// Keep closing the gripper
			containerArm.setGripperMotor(1.0);
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
