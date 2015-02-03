
package org.usfirst.frc.team79.robot;

import org.usfirst.frc.team79.robot.driveTrain.TeleopArcade;
import org.usfirst.frc.team79.subsystems.ManualController;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
    
    public void robotInit() {
    	CommandBase.init();
    }   
    
    public void teleopInit() {
    	(new TeleopArcade()).start();
    	(new ManualController()).start();
    }
    
    public void teleopPeriodic() {
    	Scheduler.getInstance().run();
    }
    
    public void autonomousInit() {
    	
    }
    
    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
    }
}
