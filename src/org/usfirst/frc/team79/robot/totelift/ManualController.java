package org.usfirst.frc.team79.robot.totelift;

import org.usfirst.frc.team79.robot.CommandBase;
import org.usfirst.frc.team79.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;

public class ManualController extends CommandBase {
	
	Joystick joystick;
	DigitalInput limitTop, limitBottom;
	
	public ManualController() {
		requires(toteManipulator);
		joystick = new Joystick(RobotMap.MANIPULATOR_STICK_ID);
		limitTop = new DigitalInput(RobotMap.TOP_LIMIT_ID);
		limitBottom = new DigitalInput(RobotMap.BOTTOM_LIMIT_ID);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {		
		toteManipulator.moveArm(joystick.getY());
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
