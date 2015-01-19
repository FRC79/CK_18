package robot.driveTrain;

import robot.CommandBase;

public class Teleop extends CommandBase {
	
	public Teleop() {
		requires(drive);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		drive.arcadeDrive(oi.joystick);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
				
	}

	@Override
	protected void interrupted() {
		
	}
	
	
}
