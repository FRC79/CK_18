package org.usfirst.frc.team79.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {
	
	public AutonomousCommand() {
		addSequential(new DriveForward());
		addSequential(new Spin());
		addSequential(new Stop());
	}

}
