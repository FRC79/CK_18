package org.usfirst.frc.team79.robot.totelift;

import org.usfirst.frc.team79.robot.RobotMap;
import org.usfirst.frc.team79.robot.util.LimitSwitch;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ToteLift extends Subsystem {
    
	VictorSP liftMotor;
	LimitSwitch bottomSwitch, topSwitch, toteCapturedSwitch;
	AnalogPotentiometer liftPot;

	public ToteLift(){
		// Init components
		liftMotor = new VictorSP(RobotMap.TOTE_LIFT_MOTOR_PORT);
		
		topSwitch = new LimitSwitch(RobotMap.TOTE_LIFT_TOP_SWITCH_PORT);
		bottomSwitch = new LimitSwitch(RobotMap.TOTE_LIFT_BOTTOM_SWITCH_PORT);
		toteCapturedSwitch = new LimitSwitch(RobotMap.TOTE_CAPTURED_SWITCH_PORT);
		
		liftPot = new AnalogPotentiometer(RobotMap.TOTE_LIFT_POT_PORT, 3600); // 10 turns * 360 degs
	}
	
	public void setMotor(double power){
		liftMotor.set(-power);	// motor is inverted
	}
	
	public double getPot(){
		return liftPot.get();
	}
	
	public boolean atBottom(){
		return bottomSwitch.get();
	}
	
	public boolean atTop(){
		return topSwitch.get();
	}
	
	public boolean hasCapturedTote(){
		return toteCapturedSwitch.get();
	}
	
    public void initDefaultCommand() {
    }
}

