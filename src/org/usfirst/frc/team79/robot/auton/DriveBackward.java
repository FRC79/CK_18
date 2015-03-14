package org.usfirst.frc.team79.robot.auton;

import org.usfirst.frc.team79.robot.drivetrain.MoveForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveBackward extends CommandGroup {
	
	public DriveBackward() {
		addSequential(new MoveForTime(0.0, -0.5, 1.25)); // Drive backwards at half speed for one second
	}
}
