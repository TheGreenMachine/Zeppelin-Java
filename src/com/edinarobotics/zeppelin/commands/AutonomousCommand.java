package com.edinarobotics.zeppelin.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {
	
	public AutonomousCommand(AutoMode automode) {
		
		switch(automode) {
			case DEFAULT:
				
				addSequential(new DriveXInchesCommand(12));
				addSequential(new RotateXDegreesCommand(180));
		}
	}
	
	public enum AutoMode {
		DEFAULT;
	}
	
}
