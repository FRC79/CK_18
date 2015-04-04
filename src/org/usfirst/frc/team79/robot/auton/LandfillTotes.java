package org.usfirst.frc.team79.robot.auton;

import org.usfirst.frc.team79.robot.drivetrain.MoveForTime;
import org.usfirst.frc.team79.robot.totelift.LiftToteToHeightFast;
import org.usfirst.frc.team79.robot.totelift.LowerLiftFastToBottom;
import org.usfirst.frc.team79.robot.util.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LandfillTotes extends CommandGroup {
	public LandfillTotes() {
		// Lower to the level of the totes
		addSequential(new LowerLiftFastToBottom()); 			// Lower to bottom
		addSequential(new WaitCommand(0.5));					// Wait to settle
		addParallel(new MoveForTime(0, 0.20, 2.5, false));	// Move into the totes
		addParallel(new WaitThenLiftTotes());  		// Should lift to certain height
	}
}
