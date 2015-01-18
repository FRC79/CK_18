package org.usfirst.frc.team79.robot;

import org.usfirst.frc.team79.robot.drivetrain.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 */

public abstract class CommandBase extends Command {

	public static OI oi;
	public static Drivetrain drivetrain;
	
	public static void init(){
		// This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
		oi = new OI();
		
		// Init subsystems
		drivetrain = new Drivetrain();
	}
	
	public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
