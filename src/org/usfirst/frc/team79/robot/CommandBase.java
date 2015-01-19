package org.usfirst.frc.team79.robot;

import org.usfirst.frc.team79.robot.drivetrain.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {
	
	public static OI oi;
	public static Drivetrain drive;

	protected static void init() {
		oi = new OI();
		drive = new Drivetrain();
	}
	
	public CommandBase() {
		super();
	}
}
