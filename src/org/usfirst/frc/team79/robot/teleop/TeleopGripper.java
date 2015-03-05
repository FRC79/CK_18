package org.usfirst.frc.team79.robot.teleop;

import org.usfirst.frc.team79.robot.CommandBase;
import org.usfirst.frc.team79.robot.gripper.Gripper;
import org.usfirst.frc.team79.robot.util.Gamepad;
import org.usfirst.frc.team79.robot.util.KUtil;

public class TeleopGripper extends CommandBase {

	public TeleopGripper() {
		requires(gripper);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		boolean RIGHT_D_PAD_PRESSED = (KUtil.deadband(OI.manipGamepad
				.getRawAxis(Gamepad.LEFT_X_AXIS)) > 0);
		boolean LEFT_D_PAD_PRESSED = (KUtil.deadband(OI.manipGamepad
				.getRawAxis(Gamepad.LEFT_X_AXIS)) < 0);
		
		// Maps the joystick buttons to the gripper movement
		if(RIGHT_D_PAD_PRESSED && !gripper.isCompletelyOpen()){
			// Open the gripper
			gripper.setMotor(Gripper.OPEN_POWER);
		} else if (LEFT_D_PAD_PRESSED && !gripper.isClosed()){
			// Close the gripper
			gripper.setMotor(Gripper.CLOSE_POWER);
		} else {
			// Stop
			gripper.stop();
		}
		
		// Fail-safe stop
		if(!RIGHT_D_PAD_PRESSED && !LEFT_D_PAD_PRESSED){
			gripper.stop();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
