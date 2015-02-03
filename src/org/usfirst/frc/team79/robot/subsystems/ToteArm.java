package org.usfirst.frc.team79.subsystems;

import org.usfirst.frc.team79.robot.RobotEnum;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ToteArm extends Subsystem {
	
	static Talon lifter;
	
	public ToteArm() {
		lifter = new Talon(RobotEnum.LIFT_SYSTEM_MOTOR.PORT);
	}
	
	public void moveArm(double speed) {
		lifter.set(speed);
	}

	@Override
	protected void initDefaultCommand() {
		
	}

}
