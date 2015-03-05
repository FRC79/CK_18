package org.usfirst.frc.team79.robot.teleop;

import org.usfirst.frc.team79.robot.CommandBase;
import org.usfirst.frc.team79.robot.containerarm.ContainerArm;
import org.usfirst.frc.team79.robot.util.Gamepad;
import org.usfirst.frc.team79.robot.util.KUtil;

public class TeleopContainerArm extends CommandBase {

	
	
	public TeleopContainerArm() {
		requires(containerArm);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		boolean Y_BUTTON_PRESSED = OI.manipGamepad.getRawButton(Gamepad.Y_BUTTON);
		boolean X_BUTTON_PRESSED = OI.manipGamepad.getRawButton(Gamepad.X_BUTTON);
		boolean JOYSTICK_NON_ZERO = KUtil.outsideDeadband(
				OI.manipGamepad.getRawAxis(Gamepad.LEFT_Y_AXIS));
		double LIFT_STICK_VALUE = OI.manipGamepad.getRawAxis(Gamepad.LEFT_Y_AXIS);
		
		// Maps gamepad buttons to lift motor
		if(Y_BUTTON_PRESSED){
			// Lift
			containerArm.setMotor(ContainerArm.LIFT_POWER);
		} else if (X_BUTTON_PRESSED){
			// Lower
			containerArm.setMotor(ContainerArm.LOWER_POWER);
		}
		
		// Maps gamepad joystick to lift motor
		if(!Y_BUTTON_PRESSED && !X_BUTTON_PRESSED){
			if(JOYSTICK_NON_ZERO){
				// Lift/Lower at the slower power
				double directionCoeff = LIFT_STICK_VALUE / Math.abs(LIFT_STICK_VALUE);
				containerArm.setMotor(ContainerArm.SLOW_POWER * directionCoeff);
			} else {
				// Stop
				containerArm.stop();
			}
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
