package org.usfirst.frc.team79.robot.containerarm;

import org.usfirst.frc.team79.robot.CommandBase;

public class LiftContainerArmForTime extends CommandBase {

	double duration = 0;

	public LiftContainerArmForTime(double duration) {
		requires(containerArm);
		
		this.duration = duration;
	}
	
	@Override
	protected void initialize() {
		setTimeout(duration);
	}

	@Override
	protected void execute() {
		containerArm.setMotor(ContainerArm.LIFT_POWER);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		containerArm.stop();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
