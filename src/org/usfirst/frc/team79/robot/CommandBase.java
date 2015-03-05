package org.usfirst.frc.team79.robot;

import org.usfirst.frc.team79.robot.containerarm.ContainerArm;
import org.usfirst.frc.team79.robot.drivetrain.Drivetrain;
import org.usfirst.frc.team79.robot.gripper.Gripper;
import org.usfirst.frc.team79.robot.totelift.ToteLift;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 */

public abstract class CommandBase extends Command {

	public static Drivetrain drivetrain;
	public static ToteLift toteLift;
	public static ContainerArm containerArm;
	public static Gripper gripper;
	
	public static void init(){
		// This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.

		// Init subsystems
		drivetrain = new Drivetrain();
		toteLift = new ToteLift();
		containerArm = new ContainerArm();
		gripper = new Gripper();
	}
	
	public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
