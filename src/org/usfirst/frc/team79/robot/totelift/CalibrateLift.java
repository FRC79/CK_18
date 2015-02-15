package org.usfirst.frc.team79.robot.totelift;

import org.usfirst.frc.team79.robot.CommandBase;
import org.usfirst.frc.team79.robot.RobotMap;
import org.usfirst.frc.team79.robot.util.KUtil;

public class CalibrateLift extends CommandBase {

	int step;
	double top, bottom;
	double LOWER_POWER = -0.12;
	double RAISE_POWER = 0.12;
	
	public CalibrateLift() {
		requires(toteLift);
	}
	
	@Override
	protected void initialize() {
		top = 1800;		// Put to half just to be safe
		bottom = 1700;
	}

	@Override
	protected void execute() {
		switch(step){
		// Calibrate top
		case 0:
			if(!toteLift.atTop()){
				toteLift.setMotor(RAISE_POWER);
			} else {
				toteLift.setMotor(0);
				top = toteLift.getPot();
				step++;
			}
			break;
			
		// Calibrate bottom
		case 1:
			if(!toteLift.atBottom()){
				toteLift.setMotor(LOWER_POWER);
			} else {
				toteLift.setMotor(0);
				bottom = toteLift.getPot();
				step++;
			}
			break;
			
		// Finally, move to center (within one revolution)
		case 2:
			if(!KUtil.withinTolerance(toteLift.getPot(), (top+bottom)/2.0, 360)){
				toteLift.setMotor(RAISE_POWER);
			} else {
				toteLift.setMotor(0);
				step++;
			}
			break;
			
		// Final settings management
		case 3:
			toteLift.setMotor(0);
			
			// Print info to user
			System.out.println();
			System.out.println("CALIBRATION VALUES");
			System.out.println("TOP: " + top);
			System.out.println("BOTTOM: " + bottom);
			System.out.println();
			
			// Save settings off to robot calibration file
			RobotMap.CALIB_CSV.putValue("TOTE_LIFT_TOP_POT_VAL", top);
			RobotMap.CALIB_CSV.putValue("TOTE_LIFT_BOTTOM_POT_VAL", bottom);
			RobotMap.CALIB_CSV.save();
			
			//Make sure values are reloaded into subsystems later on
			step++;
			break;
			
		default:
			toteLift.setMotor(0);
			break;
		}
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
