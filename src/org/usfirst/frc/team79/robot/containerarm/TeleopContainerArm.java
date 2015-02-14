package org.usfirst.frc.team79.robot.containerarm;

import org.usfirst.frc.team79.robot.CommandBase;

public class TeleopContainerArm extends CommandBase {

	public TeleopContainerArm() {
		requires(containerArm);
	}
	
	private double deadband(double joystickval) {
		return (Math.abs(joystickval) > 0.05) ? joystickval : 0.0;
	}
	
	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {

		if(Math.abs(deadband(oi.manipGamepad.getRawAxis(1))) > 0.05){
			double directionCoeff = oi.manipGamepad.getRawAxis(1) / Math.abs(oi.manipGamepad.getRawAxis(1));
			containerArm.setLiftMotor(0.12 * directionCoeff);
		} else {
			containerArm.setLiftMotor(0.0);
		}
		
		if(containerArm.atBottom() && deadband(oi.manipGamepad.getRawAxis(1)) < 0){
			containerArm.setLiftMotor(0);
		}
		
		if(containerArm.atTop() && deadband(oi.manipGamepad.getRawAxis(1)) > 0){
			containerArm.setLiftMotor(0);
		}
		
		
		
		if(oi.manipGamepad.getRawButton(5) && !containerArm.isGripperCompletelyOpen()){
			// open
			containerArm.setGripperMotor(1.0);
		} else if (oi.manipGamepad.getRawButton(6) && !containerArm.isGripperClosed()){
			// close
			containerArm.setGripperMotor(-1.0);
		} else {
			containerArm.setGripperMotor(0);
		}
		
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
