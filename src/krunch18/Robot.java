
package robot; 

import robot.commands.AutonomousCommand;
import robot.driveTrain.Teleop;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
	
	Command autonomousCommand;
    
    public void robotInit() {
    	CommandBase.init();
    	autonomousCommand = new AutonomousCommand();
    }   
    
    public void teleopInit() {
    	(new Teleop()).start();
    }
    
    public void teleopPeriodic() {
    	Scheduler.getInstance().run();
    }
    
    public void autonomousInit() {
    	autonomousCommand.start();
    }
    
    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
    }
}
