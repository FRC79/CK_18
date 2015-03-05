package org.usfirst.frc.team79.robot.containerarm;

import org.usfirst.frc.team79.robot.RobotMap;
import org.usfirst.frc.team79.robot.util.LimitSwitch;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/* 
 * 	Container Arm Subsystem
 */

public class ContainerArm extends Subsystem {

	public static double SLOW_POWER = 0.25;
	public static double LIFT_POWER = 0.60;
	public static double LOWER_POWER = -0.50;
	
	VictorSP liftMotor;
	LimitSwitch topSwitch, bottomSwitch;

	public ContainerArm() {
		// Init components
		liftMotor = new VictorSP(RobotMap.CONTAINER_ARM_MOTOR_PORT);

		topSwitch = new LimitSwitch(RobotMap.CONATINER_ARM_TOP_SWITCH_PORT);
		bottomSwitch = new LimitSwitch(
				RobotMap.CONTAINER_ARM_BOTTOM_SWITCH_PORT);
	}

	public void setMotor(double power) {
		liftMotor.set(power);
	}
	
	public void stop(){
		setMotor(0);
	}

	public boolean atTop() {
		return topSwitch.get();
	}

	public boolean atBottom() {
		return bottomSwitch.get();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new StopContainerArm());
	}

}
