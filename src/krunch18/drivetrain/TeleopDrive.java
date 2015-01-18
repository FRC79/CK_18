package krunch18.drivetrain;

import krunch18.CommandBase;

/**
 *
 */
public class TeleopDrive extends CommandBase {

	static final double JOYSTICK_DEADBAND = 0.05;
	static final double ANGLE_DELTA_TOLERANCE = 0.05;
	
	boolean userNowRotating, userWasRotating, changeInTheta, stoppingRotation;
	double lastGyroVal;
	
    public TeleopDrive() {
    	requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	userNowRotating = false;
    	userWasRotating = false;
    	changeInTheta = false;
    	stoppingRotation = false;
    	lastGyroVal = 0.0;
    	
    	drivetrain.gyro.reset();	// Reset heading to 0 degs
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Update current state variable (Is the user rotating?)
    	userNowRotating = (Math.abs(oi.rotStick.getX()) > JOYSTICK_DEADBAND);
    	changeInTheta = (Math.abs(drivetrain.getGyro() - lastGyroVal) > ANGLE_DELTA_TOLERANCE);
    	
    	// Performs gyro-stabilization when translating to eliminate drift
    	double rotVal = 0.0;
    	if(userNowRotating){
    		// Rotate at user input power
    		rotVal = oi.rotStick.getX();
    	} else {
    		if(userWasRotating){
    			// Begin to decelerate the angular rotation
    			stoppingRotation = true;
    		}
    		
    		// Continue to set rotation to 0 unless robot has finally stopped rotating
    		if(stoppingRotation){
    			if(!changeInTheta){
    				// Robot has stopped rotating, so now reset the heading
    				drivetrain.resetGyro();
    				drivetrain.resetGyro();
    				drivetrain.resetGyro();
    				drivetrain.resetGyro();
    				drivetrain.resetGyro();
    				stoppingRotation = false;
    			}
    		} else {
    			// Robot should not be rotating, therefore perform auto-stabilization on the heading
    			rotVal = -drivetrain.getGyro()*drivetrain.kP;
    		}
    	}
    	
    	// Maps 3-axis joystick to mecanum drive (X, Y, Rotation)
    	drivetrain.driveXY(oi.moveStick.getX(), oi.moveStick.getY(), rotVal);

    	// Update previous state variable (Was the user just rotating?)
    	userWasRotating = userNowRotating;
    	lastGyroVal = drivetrain.getGyro();    
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
