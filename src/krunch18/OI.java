package robot;

import robot.driveTrain.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {
	
	public static OI oi;
	public static DriveTrain drive;

	protected static void init() {
		oi = new OI();
		drive = new DriveTrain();
	}
	
	public CommandBase() {
		super();
	}
}

