package org.usfirst.frc.team79.robot;

import org.usfirst.frc.team79.robot.drivetrain.Drivetrain;
import org.usfirst.frc.team79.robot.totelift.ToteLifter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 */

public abstract class CommandBase extends Command {

	public static OI oi;
	public static Drivetrain drivetrain;
	public static ToteLifter toteManipulator;
	
	public static void init(){

		oi = new OI();
		drivetrain = new Drivetrain();
		toteManipulator = new ToteLifter();
		
	}
	
	public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
