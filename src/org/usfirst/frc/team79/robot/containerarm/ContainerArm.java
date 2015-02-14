package org.usfirst.frc.team79.robot.containerarm;

import org.usfirst.frc.team79.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ContainerArm extends Subsystem {

	VictorSP liftMotor, gripperMotor;
	DigitalInput topSwitch, bottomSwitch, gripperOpenSwitch,
			gripperClosedSwitch;

	public ContainerArm() {
		liftMotor = new VictorSP(RobotMap.CONTAINER_ARM_MOTOR_PORT);
		gripperMotor = new VictorSP(RobotMap.CONTAINER_GRIPPER_MOTOR_PORT);

		topSwitch = new DigitalInput(RobotMap.CONATINER_ARM_TOP_SWITCH_PORT);
		bottomSwitch = new DigitalInput(
				RobotMap.CONTAINER_ARM_BOTTOM_SWITCH_PORT);

		gripperOpenSwitch = new DigitalInput(
				RobotMap.CONTAINER_GRIPPER_OPEN_SWITCH_PORT);
		gripperClosedSwitch = new DigitalInput(
				RobotMap.CONTAINER_GRIPPER_CLOSED_SWITCH_PORT);
	}

	public void setLiftMotor(double power) {
		liftMotor.set(-power);
	}

	public void setGripperMotor(double power) {
		gripperMotor.set(power);
	}

	public boolean isGripperClosed() {
		return gripperClosedSwitch.get();
	}

	public boolean isGripperCompletelyOpen() {
		return gripperOpenSwitch.get();
	}

	public boolean atTop() {
		return topSwitch.get();
	}

	public boolean atBottom() {
		return bottomSwitch.get();
	}

	@Override
	protected void initDefaultCommand() {

	}

}
