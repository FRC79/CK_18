package org.usfirst.frc.team79.robot.commands;

import org.usfirst.frc.team79.robot.CommandBase;
import org.usfirst.frc.team79.robot.RobotMap;

public class JeremysController extends CommandBase {
	
	public JeremysController() {
		requires(totelifter);
		requires(binlifter);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		
		if(oi.toteLiftDown.get()) {
			totelifter.move(-1.0);
		} else if(oi.toteLiftUp.get()){
			totelifter.move(1.0);
		} else {
			totelifter.move(oi.moveStick.getRawAxis(RobotMap.LIFT_RAW_VALUE));
		}
		
		binlifter.armPosition(oi.moveStick.getRawAxis(RobotMap.GRIPPER_ARM_RAW_VALUE));
		binlifter.grip(oi.moveStick.getRawAxis(RobotMap.GRIPPER_RAW_VALUE));
		
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
