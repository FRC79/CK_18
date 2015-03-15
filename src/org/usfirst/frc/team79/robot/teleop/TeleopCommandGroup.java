package org.usfirst.frc.team79.robot.teleop;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	Teleop main entry command: All teleop containers start from here.
 */
public class TeleopCommandGroup extends CommandGroup {
    
    public  TeleopCommandGroup() {
    	addParallel(new TeleopDrive());
    	addParallel(new TeleopLift());
    	addParallel(new TeleopContainerArm());
    	addParallel(new TeleopGripper());
    }
}
