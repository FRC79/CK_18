package org.usfirst.frc.team79.robot.totelift;

import org.usfirst.frc.team79.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ToteLifter extends Subsystem {
	
	static Talon lifter;
	DigitalInput limitTop, limitBottom;
	
	public ToteLifter() {
		lifter = new Talon(RobotMap.LIFT_SYSTEM_MOTOR);
		limitTop = new DigitalInput(0);
		limitBottom = new DigitalInput(1);
	}
	
	public void moveArm(double speed) {
		
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
