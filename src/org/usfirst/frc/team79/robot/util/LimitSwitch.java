package org.usfirst.frc.team79.robot.util;

import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSwitch extends DigitalInput {

	public LimitSwitch(int channel) {
		super(channel);
	}

	@Override
	public boolean get() {
		return !super.get();	// Flips the value to compensate for the pull-up resistor
	}
}
