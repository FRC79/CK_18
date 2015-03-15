package org.usfirst.frc.team79.robot.auton;

import org.usfirst.frc.team79.robot.drivetrain.MoveForTime;
import org.usfirst.frc.team79.robot.totelift.LiftToteToHeightFast;
import org.usfirst.frc.team79.robot.totelift.SnapToteInPlace;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SingleTote extends CommandGroup {
	public SingleTote() {
		// Pick up tote
		addSequential(new SnapToteInPlace()); 			// Snap the container in place
		addSequential(new LiftToteToHeightFast(2600));  // Should lift to half way up
	
		// Drive Into auto zone
		addSequential(new MoveForTime(1.0, 0, 1.0, false)); // Move sideways
	}
}
