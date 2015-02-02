package org.usfirst.frc.team79.robot.drivetrain;

import org.usfirst.frc.team79.robot.CommandBase;

/**
 *
 */
public class StopDriveMotors extends CommandBase {

	private boolean isFinished, runsForever;
    
    public StopDriveMotors() {
        this(false);
    }
    
    public StopDriveMotors(boolean runContinuously){
        requires(drivetrain);
        runsForever = runContinuously;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // Run this infinitely so that something else has to stop it.
        drivetrain.stop();
        
        if(!runsForever){
            isFinished = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
