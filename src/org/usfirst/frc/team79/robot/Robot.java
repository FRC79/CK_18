
package org.usfirst.frc.team79.robot;

import org.usfirst.frc.team79.robot.commands.JeremysController;
import org.usfirst.frc.team79.robot.drivetrain.TeleopDrive;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	SendableChooser joystickMode, controllerMode;
	
    public void robotInit() {

    	CommandBase.init();
    	
    	controllerMode = new SendableChooser();
    	controllerMode.addDefault("Connors Controller", 1);
    	controllerMode.addObject("Jeremys Controller", 2);
    	SmartDashboard.putData("CONTROLLER MODE", controllerMode);

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
    	
    	if((int)controllerMode.getSelected() == 1) {
        	(new ConnorsController()).start();
    	} else {
    		(new JeremysController()).start();
    	}
    	
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
