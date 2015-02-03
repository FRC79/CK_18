package org.usfirst.frc.team79.robot;

import org.usfirst.frc.team79.robot.driveTrain.DriveTrain;
import org.usfirst.frc.team79.subsystems.ToteArm;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {
	
	public static OI oi;
	public static DriveTrain drive;
	public static ToteArm toteManipulator;

	protected static void init() {
		oi = new OI();
		drive = new DriveTrain();
		toteManipulator = new ToteArm();
	}
	
	public CommandBase() {
		super();
	}
}

