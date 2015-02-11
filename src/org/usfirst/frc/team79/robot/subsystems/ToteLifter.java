package org.usfirst.frc.team79.robot.subsystems;

import org.usfirst.frc.team79.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ToteLifter extends Subsystem {
	
	static Victor lifter;
	DigitalInput limitTop, limitBottom;
	
	public ToteLifter() {
		lifter = new Victor(RobotMap.LIFT_SYSTEM_MOTOR);
		limitTop = new DigitalInput(RobotMap.LIFT_TOP_LIMIT_ID);
		limitBottom = new DigitalInput(RobotMap.LIFT_BOTTOM_LIMIT_ID);
	}
	
	public void move(double speed) {
		
		if(limitTop.get() && (speed > 0)) {
			speed = 0;
		}
		
		if(limitBottom.get() && (speed < 0)) {
			speed = 0;
		}
		
		lifter.set(speed);
	}

	@Override
	protected void initDefaultCommand() {
		
	}

}
