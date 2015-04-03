package org.usfirst.frc.team79.robot.auton;

import org.usfirst.frc.team79.robot.totelift.LiftToteToHeightFast;
import org.usfirst.frc.team79.robot.util.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WaitThenLiftTotes extends CommandGroup {
	public WaitThenLiftTotes() {
		addSequential(new WaitCommand(2.0));
		addSequential(new LiftToteToHeightFast(2160));
	}
}
