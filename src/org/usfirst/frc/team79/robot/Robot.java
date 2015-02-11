
package org.usfirst.frc.team79.robot;

import org.usfirst.frc.team79.robot.commands.ConnorsController;
import org.usfirst.frc.team79.robot.drivetrain.TeleopDrive;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	SendableChooser joystickMode;
	
    public void robotInit() {

    	CommandBase.init();

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
    	(new ConnorsController()).start();
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
