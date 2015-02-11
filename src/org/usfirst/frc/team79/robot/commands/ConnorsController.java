package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.CommandBase;
import org.usfirst.frc.team79.robot.RobotMap;

public class ConnorsController extends CommandBase {
	
	public ConnorsController() {
		requires(totelifter);
		requires(binlifter);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		
		if(oi.toteLiftDown.get()) {
			oi.toteLiftDown.whileHeld(new ToteLift(-1.0));
		} else if(oi.toteLiftUp.get()){
			oi.toteLiftUp.whileHeld(new ToteLift(1.0));
		} else {
			totelifter.move(oi.moveStick.getRawAxis(RobotMap.LIFT_RAW_VALUE));
		}
		
		binlifter.armPosition(oi.moveStick.getRawAxis(RobotMap.GRIPPER_ARM_RAW_VALUE));
		binlifter.grip(oi.moveStick.getRawAxis(RobotMap.GRIPPER_RAW_VALUE));
		
		//oi.gripperClose.whileHeld(new Gripper(-1.0));
		//oi.gripperOpen.whileHeld(new Gripper(1.0));
		
		//oi.gripperArmDown.whileActive(new MoveGripperArm(-1.0));
		//oi.gripperArmUp.whileActive(new MoveGripperArm(1.0));
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
