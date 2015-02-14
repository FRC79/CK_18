package org.usfirst.frc.team79.robot.drivetrain;

import org.usfirst.frc.team79.robot.CommandBase;

public class TeleopDrive extends CommandBase {

	public TeleopDrive() {
		requires(drivetrain);
	}

	private double getJoystickRot() {
		return oi.driverJoystick.getRawAxis(3);
	}

	private double deadband(double joystickval) {
		return (Math.abs(joystickval) > 0.05) ? joystickval : 0.0;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		// Maps 3-axis joystick to mecanum drive (X, Y, Rotation)
		drivetrain.driveXY(deadband(oi.driverJoystick.getX()),
				deadband(oi.driverJoystick.getY()), deadband(getJoystickRot()));

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
