
package org.usfirst.frc.team79.robot;

import org.usfirst.frc.team79.robot.auton.DoNothing;
import org.usfirst.frc.team79.robot.auton.DriveBackward;
import org.usfirst.frc.team79.robot.auton.SingleTote;
import org.usfirst.frc.team79.robot.teleop.OI;
import org.usfirst.frc.team79.robot.teleop.TeleopCommandGroup;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	SendableChooser autonSelector;
	
    public void robotInit() {
    	System.out.println();
    	System.out.println("--- robotInit() STARTED ------------------");
    	System.out.println();
    	
    	// Load settings and init subsystems (MUST GO IN THIS ORDER)
    	RobotMap.init();
    	OI.init();
    	CommandBase.init();
    	
    	autonSelector = new SendableChooser();
    	autonSelector.addDefault("Drive backwards", new DriveBackward());
    	autonSelector.addObject("Do nothing", new DoNothing());
    	autonSelector.addObject("Single tote", new SingleTote());
    	
    	SmartDashboard.putData("Autonomous Mode", autonSelector);
    	
    	System.out.println();
    	System.out.println("--- robotInit() FINISHED ------------------");
    	System.out.println();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	Command autonCommand = (Command) autonSelector.getSelected();
    	autonCommand.start();
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
