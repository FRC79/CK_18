
package org.usfirst.frc.team79.robot;

import org.usfirst.frc.team79.robot.teleop.OI;
import org.usfirst.frc.team79.robot.teleop.TeleopCommandGroup;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
    public void robotInit() {
    	System.out.println();
    	System.out.println("--- robotInit() STARTED ------------------");
    	System.out.println();
    	
    	// Load settings and init subsystems (MUST GO IN THIS ORDER)
    	RobotMap.init();
    	OI.init();
    	CommandBase.init();
    	
    	System.out.println();
    	System.out.println("--- robotInit() FINISHED ------------------");
    	System.out.println();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	// Start the teleop command group
    	(new TeleopCommandGroup()).start();
    }

    public void disabledInit(){

    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic() {
//        LiveWindow.run();
    }
}
