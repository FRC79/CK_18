package org.usfirst.frc.team79.robot.teleop;

import org.usfirst.frc.team79.robot.CommandBase;
import org.usfirst.frc.team79.robot.util.KUtil;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * 	Teleop Drivetain command for taking direct input from the
 *  driver joystick and map to the drivetrain w/o gyro correction.
 */

public class TeleopDrive extends CommandBase {

	public TeleopDrive() {
		requires(drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		// Maps 3-axis joystick to mecanum drive (X, Y, Rotation)
		drivetrain.move(KUtil.deadband(OI.driverJoystick.getX()),
				KUtil.deadband(OI.driverJoystick.getY()) * 0.85, KUtil.deadband(OI.getDriveRot()) * 0.85);

		
		SmartDashboard.putNumber("GYRO", drivetrain.getGyro());
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
