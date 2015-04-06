package org.usfirst.frc.team79.robot.auton;

import org.usfirst.frc.team79.robot.gripper.CloseGripperForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GrabContainer extends CommandGroup {

	public GrabContainer() {
		// Pick up container
		addSequential(new CloseGripperForTime(1.0));
	}
}
