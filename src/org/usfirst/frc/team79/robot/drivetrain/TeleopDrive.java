package org.usfirst.frc.team79.robot.drivetrain;

import org.usfirst.frc.team79.robot.CommandBase;
import org.usfirst.frc.team79.robot.util.KUtil;

public class TeleopDrive extends CommandBase {

	public TeleopDrive() {
		requires(drivetrain);
	}

	private double getJoystickRot() {
		return oi.driverJoystick.getRawAxis(3);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		// Maps 3-axis joystick to mecanum drive (X, Y, Rotation)
		drivetrain.driveXY(KUtil.deadband(oi.driverJoystick.getX()),
				KUtil.deadband(oi.driverJoystick.getY()), KUtil.deadband(getJoystickRot()));

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
