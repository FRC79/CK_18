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
		addSequential(new MoveForTime(0, 0.25, 0.200, false));	// Move into the totes
		addSequential(new WaitCommand(1.0)); 					// Wait to settle down
		addSequential(new LiftToteToHeightFast(2160));  		// Should lift to certain height
	}
}
