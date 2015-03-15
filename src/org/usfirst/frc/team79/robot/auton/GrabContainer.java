package org.usfirst.frc.team79.robot.auton;

import org.usfirst.frc.team79.robot.containerarm.LiftContainerArmForTime;
import org.usfirst.frc.team79.robot.gripper.CloseGripper;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GrabContainer extends CommandGroup {

	public GrabContainer() {
		// Pick up container
		addSequential(new CloseGripper());
		addSequential(new LiftContainerArmForTime(0.6));
	}
}
