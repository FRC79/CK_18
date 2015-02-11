package org.usfirst.frc.team79.robot;

import org.usfirst.frc.team79.robot.drivetrain.Drivetrain;
import org.usfirst.frc.team79.robot.subsystems.Gripper;
import org.usfirst.frc.team79.robot.subsystems.ToteLifter;


import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {

	public static OI oi;
	public static Drivetrain drivetrain;
	public static ToteLifter totelifter;
	public static Gripper binlifter;
	
	public static void init(){
		oi = new OI();
		totelifter = new ToteLifter();
		binlifter = new Gripper();
		drivetrain = new Drivetrain();
	}
	
	public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
