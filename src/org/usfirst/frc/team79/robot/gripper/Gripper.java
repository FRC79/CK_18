package org.usfirst.frc.team79.robot.gripper;

import org.usfirst.frc.team79.robot.RobotMap;
import org.usfirst.frc.team79.robot.util.LimitSwitch;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/*
 * 	Gripper subsystem
 */

public class Gripper extends Subsystem {

	public static double OPEN_POWER = 0.50;
	public static double CLOSE_POWER = -0.50;
	
	private VictorSP gripperMotor;
	private LimitSwitch openSwitch, closedSwitch;

	public Gripper() {
		// Init components
		gripperMotor = new VictorSP(RobotMap.CONTAINER_GRIPPER_MOTOR_PORT);
		
		openSwitch = new LimitSwitch(
				RobotMap.CONTAINER_GRIPPER_OPEN_SWITCH_PORT);
		closedSwitch = new LimitSwitch(
				RobotMap.CONTAINER_GRIPPER_CLOSED_SWITCH_PORT);
	}
	
	public void setMotor(double power) {
		gripperMotor.set(-power);
	}
	
	public void stop(){
		setMotor(0);
	}

	public boolean isClosed() {
		return closedSwitch.get();
	}

	public boolean isCompletelyOpen() {
		return openSwitch.get();
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new StopGripper());
	}

}
