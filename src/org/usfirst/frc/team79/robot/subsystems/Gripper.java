package org.usfirst.frc.team79.robot.subsystems;

import org.usfirst.frc.team79.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Gripper extends Subsystem {
	
	DigitalInput GRIPPER_CLOSE, GRIPPER_OPEN, ACTUATOR_TOP, ACTUATOR_BOTTOM;
	
	static Victor gripper;
	static Victor mover;
	
	
	public Gripper() {
		gripper = new Victor(RobotMap.GRIPPER_MOTOR);
		mover = new Victor(RobotMap.GRIPPER_ARM_MOTOR);
		
		GRIPPER_CLOSE = new DigitalInput(RobotMap.GRIP_CLOSE_LIMIT_ID);
		GRIPPER_OPEN = new DigitalInput(RobotMap.GRIP_OPEN_LIMIT_ID);
		
		ACTUATOR_TOP = new DigitalInput(RobotMap.ACTUATOR_TOP_ID);
		ACTUATOR_BOTTOM = new DigitalInput(RobotMap.ACTUAT0R_BOTTOM_ID);
	}
	
	public void grip(double value) {
		if(GRIPPER_OPEN.get() && value > 0) {
			value = 0;
		}
		
		if(GRIPPER_CLOSE.get() && value < 0) {
			value = 0;
		}
		
		mover.set(value);
	}
	
	public void armPosition(double value) {
		if(ACTUATOR_TOP.get() && value > 0) {
			value = 0;
		}
		
		if(ACTUATOR_BOTTOM.get() && value < 0) {
			value = 0;
		}
		
		mover.set(value);
	}

	@Override
	protected void initDefaultCommand() {
	
	}

}
