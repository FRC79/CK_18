package org.usfirst.frc.team79.robot.containerarm;

import org.usfirst.frc.team79.robot.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

		if(containerArm.atBottom() && deadband(-oi.manipGamepad.getRawAxis(1)) < 0){
			containerArm.setLiftMotor(0);
		} else if(containerArm.atTop() && deadband(-oi.manipGamepad.getRawAxis(1)) > 0){
			containerArm.setLiftMotor(0);
		} else {
			if(Math.abs(-oi.manipGamepad.getRawAxis(1)) > 0.05){
				double directionCoeff = -oi.manipGamepad.getRawAxis(1) / Math.abs(-oi.manipGamepad.getRawAxis(1));
				containerArm.setLiftMotor(0.25 * directionCoeff);
			} else {
				containerArm.setLiftMotor(0.0);
			}
		}
		

		SmartDashboard.putBoolean("JOYSTICK POS", deadband(-oi.manipGamepad.getRawAxis(1)) > 0);
		SmartDashboard.putBoolean("JOYSTICK NEG", deadband(-oi.manipGamepad.getRawAxis(1)) < 0);
		
		if(oi.manipGamepad.getRawButton(3) && !containerArm.isGripperCompletelyOpen()){
			// open
			containerArm.setGripperMotor(1.0);
		} else if (oi.manipGamepad.getRawButton(2) && !containerArm.isGripperClosed()){
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
