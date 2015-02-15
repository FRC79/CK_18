package org.usfirst.frc.team79.robot.totelift;

import org.usfirst.frc.team79.robot.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopLift extends CommandBase {

	public TeleopLift(){
		requires(toteLift);
	}
	
	private double deadband(double joystickval) {
		return (Math.abs(joystickval) > 0.05) ? joystickval : 0.0;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		SmartDashboard.putNumber("LIFT POT", toteLift.getPot());
		
		if(toteLift.atBottom() && deadband(-oi.manipGamepad.getRawAxis(3)) < 0){
			toteLift.setMotor(0);
		} else if(toteLift.atTop() && deadband(-oi.manipGamepad.getRawAxis(3)) > 0){
			toteLift.setMotor(0);
		} else {
			toteLift.setMotor(Math.pow(deadband(-oi.manipGamepad.getRawAxis(3)), 3));
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