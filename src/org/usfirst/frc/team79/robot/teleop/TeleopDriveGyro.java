package org.usfirst.frc.team79.robot.teleop;

import org.usfirst.frc.team79.robot.CommandBase;
import org.usfirst.frc.team79.robot.util.KUtil;

/*
 * 	Teleop drive command that takes direct input from the joystick and maps
 * 	it to the drive motors w/ correction from the gyro to maintain orientation.
 */

public class TeleopDriveGyro extends CommandBase {

	static final double ANGLE_DELTA_TOLERANCE = 0.05;	// Tolerance in between samples of gyro
														// change before a correction needs to be made

	boolean userNowRotating, userWasRotating, changeInTheta, stoppingRotation; // state variables for correction
	double lastGyroVal;

	public TeleopDriveGyro() {
		requires(drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Reset all state variables
		userNowRotating = false;
		userWasRotating = false;
		changeInTheta = false;
		stoppingRotation = false;
		lastGyroVal = 0.0;

		drivetrain.resetGyro(); // Reset heading to 0 degs
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Update current state variable (Is the user rotating?)
		userNowRotating = KUtil.outsideDeadband(OI.getDriveRot());
		changeInTheta = KUtil.outsideTolerance(drivetrain.getGyro(), lastGyroVal, ANGLE_DELTA_TOLERANCE);

		// Performs gyro-stabilization when translating to eliminate drift
		double rotVal = 0.0;
		if (userNowRotating) {
			// Rotate at user input power
			rotVal = KUtil.deadband(OI.getDriveRot());
		} else {
			if (userWasRotating) {
				// Begin to decelerate the angular rotation
				stoppingRotation = true;
			}

			// Continue to set rotation to 0 unless robot has finally stopped
			// rotating
			if (stoppingRotation) {
				if (!changeInTheta) {
					// Robot has stopped rotating, so now reset the heading
					drivetrain.resetGyro();
					drivetrain.resetGyro();
					drivetrain.resetGyro();
					drivetrain.resetGyro();
					drivetrain.resetGyro();
					stoppingRotation = false;
				}
			} else {
				// Robot should not be rotating, therefore perform
				// auto-stabilization on the heading
				rotVal = -drivetrain.getGyro() * drivetrain.kP;
			}
		}

		// Maps 3-axis joystick to mecanum drive (X, Y, Rotation)
		drivetrain.move(KUtil.deadband(OI.driverJoystick.getX()),
				KUtil.deadband(OI.driverJoystick.getY()), rotVal);

		// Update previous state variable (Was the user just rotating?)
		userWasRotating = userNowRotating;
		lastGyroVal = drivetrain.getGyro();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
