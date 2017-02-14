package com.edinarobotics.zeppelin.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {
	
	public AutonomousCommand(AutoMode automode) {
		
		switch(automode) {
		
			case BASELINE:				
				addSequential(new DriveXInchesCommand(112));
				
				break;
				
			case NOTHING:
				
				break;
				
			default:
				
				break;

		}
	}
	
	public enum AutoMode {
		BASELINE, NOTHING;
	}
	
}
