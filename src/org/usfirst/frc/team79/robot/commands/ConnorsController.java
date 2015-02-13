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
		
		// Tote lifter mechanism
		
		if(Math.abs(oi.controlPad.getRawAxis(RobotMap.LIFT_RAW_VALUE)) > 0.05) {
			totelifter.move(oi.controlPad.getRawAxis(RobotMap.LIFT_RAW_VALUE));
		} else {
			totelifter.move(0.0);
		}
		
		if(oi.controlPad.getRawButton(3)) {
			totelifter.move(-1.0);
		} else if(oi.controlPad.getRawButton(2)){
			totelifter.move(1.0);
		} else {
			totelifter.move(0);
		}
		
		// Positioning of linear actuator
		
		if(Math.abs(oi.controlPad.getRawAxis(RobotMap.GRIPPER_ARM_RAW_VALUE)) > 0.05) {
			binlifter.armPosition(oi.controlPad.getRawAxis(RobotMap.GRIPPER_ARM_RAW_VALUE));
		} else {
			binlifter.armPosition(0.0);
		}
		
		// Positioning of the gripper 
		
		if(Math.abs(oi.controlPad.getRawAxis(RobotMap.GRIPPER_RAW_VALUE)) > 0.05) {
			binlifter.grip(oi.controlPad.getRawAxis(RobotMap.GRIPPER_RAW_VALUE));
		} else {
			binlifter.grip(0.0);
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
