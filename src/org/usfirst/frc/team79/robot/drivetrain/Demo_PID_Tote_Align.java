package org.usfirst.frc.team79.robot.drivetrain;

import org.usfirst.frc.team79.robot.CommandBase;
import org.usfirst.frc.team79.robot.camera.VisionService;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Demo_PID_Tote_Align extends CommandBase {

	VisionService vision;
	static final double kP = 0.50;
	
    public Demo_PID_Tote_Align() {
    	requires(drivetrain);
    	vision = VisionService.getInstance();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(vision.cameraConnected() && vision.processingImage()){
    		if(vision.rightTargetExists()){
    			// PID
    			drivetrain.moveXY_AS(-vision.getErrorFromRightTarget() * kP, 0.0); // may want to square output
    			
    			SmartDashboard.putNumber("ERROR", vision.getErrorFromRightTarget());
    		} else {
    			if(vision.leftTargetExists()){
    				// Drive right until you see the right target
    				drivetrain.moveXY_AS(0.50, 0.0);
    			} else {
    				// Stop for testing purposes
    				drivetrain.stop();
    			}
    			
    			SmartDashboard.putNumber("ERROR", 0.0);
    		}
    	} else {
    		drivetrain.stop();
    	}
    	
    	Timer.delay(0.015);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
