
package org.usfirst.frc.team79.robot;

import org.usfirst.frc.team79.robot.drivetrain.TeleopDrive;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
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
	
	SendableChooser joystickMode;
	
    public void robotInit() {
    	// Load settings and init subsystems
//    	RobotMap.loadCSVSettings();
    	CommandBase.init();
    	
    	// Selecting joystick modes for mecanum
    	joystickMode = new SendableChooser();
    	joystickMode.addDefault("Single Joystick", OI.MODE_SINGLE_JOYSTICK);
    	joystickMode.addObject("Dual Joysticks", OI.MODE_DUAL_JOYSTICKS);
    	SmartDashboard.putData("JOYSTICK MODE", joystickMode);
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
    	(new TeleopDrive((int)joystickMode.getSelected())).start();
    }

    public void disabledInit(){

    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
