package org.usfirst.frc.team79.robot.containerarm;

import org.usfirst.frc.team79.robot.CommandBase;

public class LowerContainerArmForTime extends CommandBase {

	double power = 0;
	double duration = 0;
	
	public LowerContainerArmForTime(double power, double duration) {
		requires(containerArm);
	
		this.power = power;
		this.duration = duration;
	}
	
	@Override
	protected void initialize() {
		setTimeout(duration);
	}

	@Override
	protected void execute() {
		containerArm.setMotor(power);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isTimedOut();
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		containerArm.stop();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
