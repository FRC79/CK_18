package org.usfirst.frc.team79.robot.auton;

import org.usfirst.frc.team79.robot.drivetrain.MoveForTime;
import org.usfirst.frc.team79.robot.totelift.LiftToteToHeightFast;
import org.usfirst.frc.team79.robot.totelift.LowerLiftFast;
import org.usfirst.frc.team79.robot.util.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LandfillTotes extends CommandGroup {
	public LandfillTotes() {
		// Lower to the level of the totes
		addSequential(new LowerLiftFast()); 					// Lower to bottom
		addSequential(new MoveForTime(0, 0.25, 0.100, false));	// Move into the totes
		addSequential(new WaitCommand(0.5)); 					// Wait to settle down
		addSequential(new LiftToteToHeightFast(2600));  		// Should lift to certain height
	}
}
