package org.usfirst.frc.team79.robot.gripper;

import org.usfirst.frc.team79.robot.CommandBase;

public class CloseGripperForTime extends CommandBase {

	double duration;
	
	public CloseGripperForTime(double time) {
		requires(gripper);
		
		this.duration = time;
	}
	
	@Override
	protected void initialize() {
		setTimeout(duration);
	}

	@Override
	protected void execute() {
		gripper.setMotor(Gripper.CLOSE_POWER);
	}

	@Override
	protected boolean isFinished() {
		return (gripper.isClosed() || isTimedOut());
	}

	@Override
	protected void end() {
		gripper.stop();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
