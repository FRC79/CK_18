package org.usfirst.frc.team79.robot.subsystems;

import org.usfirst.frc.team79.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Gripper extends Subsystem {
	
	DigitalInput gripperClosed, gripperOpen, actuatorTopped, actuatorBottomed;
	
	static Victor gripper;
	static Victor mover;
	
	
	public Gripper() {
		gripper = new Victor(RobotMap.GRIPPER_MOTOR);
		mover = new Victor(RobotMap.GRIPPER_ARM_MOTOR);
		
		gripperClosed = new DigitalInput(RobotMap.GRIP_CLOSE_LIMIT_ID);
		gripperOpen = new DigitalInput(RobotMap.GRIP_OPEN_LIMIT_ID);
		
		actuatorTopped = new DigitalInput(RobotMap.ACTUATOR_TOP_ID);
		actuatorBottomed = new DigitalInput(RobotMap.ACTUAT0R_BOTTOM_ID);
	}
	
	public void grip(double value) {
		if(gripperOpen.get() && value > 0) {
			value = 0;
		}
		
		if(gripperClosed.get() && value < 0) {
			value = 0;
		}
		
		mover.set(value);
	}
	
	public void armPosition(double value) {
		if(actuatorTopped.get() && value > 0) {
			value = 0;
		}
		
		if(actuatorBottomed.get() && value < 0) {
			value = 0;
		}
		
		mover.set(value);
	}

	@Override
	protected void initDefaultCommand() {
	
	}

}
