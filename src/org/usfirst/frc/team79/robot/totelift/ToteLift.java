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
	LimitSwitch bottomSwitch, topSwitch;
	AnalogPotentiometer liftPot;

	public static double LIFT_POWER = 1.0;
	public static double LOWER_POWER = -0.25;
	public static double SLOW_LOWER_POWER = -0.12;
	public static double SLOW_LIFT_POWER = 0.5;
	public static double SNAP_DOWN_POWER = -0.50;
	
	public static double TOP_POT_STOP = 3320;
	public static double BOTTOM_POT_SLOWER_STOP = 2000;
	public static double BOTTOM_POT_STOP = 1900;
	
	public ToteLift(){
		// Init components
		liftMotor = new VictorSP(RobotMap.TOTE_LIFT_MOTOR_PORT);
		
		topSwitch = new LimitSwitch(RobotMap.TOTE_LIFT_TOP_SWITCH_PORT);
		bottomSwitch = new LimitSwitch(RobotMap.TOTE_LIFT_BOTTOM_SWITCH_PORT);
		
		liftPot = new AnalogPotentiometer(RobotMap.TOTE_LIFT_POT_PORT, 3600); // 10 turns * 360 degs
	}
	
	public void setMotor(double power){
		liftMotor.set(-power);	// motor is inverted
	}
	
	public double getPot(){
		return liftPot.get();
	}
	
    public void initDefaultCommand() {
    }
}

