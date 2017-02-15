package com.edinarobotics.zeppelin.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {
	
	public AutonomousCommand(AutoMode automode) {
		
		switch(automode) {
		
			case CENTER_GEAR:
				
				break;
				
			case LEFT_GEAR:
				
				break;
				
			case RIGHT_GEAR:
				
				break;
		
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
		CENTER_GEAR,
		LEFT_GEAR,
		RIGHT_GEAR,
		BASELINE, 
		NOTHING;
	}
	
}
