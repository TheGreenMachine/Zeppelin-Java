package com.edinarobotics.zeppelin.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {
	
	public AutonomousCommand(AutoMode automode) {
		
		switch(automode) {
		
			case TEST:
			
				addSequential(new DriveXInchesVerticalCommand(0.7, 112));
				addSequential(new RotateXDegreesCommand(55));
				addSequential(new RunVisionStrafeCommand());
				addSequential(new DriveXInchesVerticalCommand(0.7, 24));
				addSequential(new RunVisionStrafeCommand());
				addSequential(new DriveXInchesVerticalCommand(0.7, 23));
				
				break;
				
			case TEST_LEFT:
				
				addSequential(new DriveXInchesVerticalCommand(0.7, 112));
				addSequential(new RotateXDegreesCommand(-60));
				addSequential(new RunVisionStrafeCommand());
				addSequential(new DriveXInchesVerticalCommand(0.7, 24));
				addSequential(new RunVisionStrafeCommand());
				addSequential(new DriveXInchesVerticalCommand(0.7, 23));
				
				break;
		
			case CENTER_GEAR:
				
				break;
				
			case LEFT_GEAR:
				
				break;
				
			case RIGHT_GEAR:
				
				break;
		
			case BASELINE:				
				addSequential(new DriveXInchesVerticalCommand(0.7, 112));
				
				break;
				
			case NOTHING:
				
				break;
				
			default:
				
				break;

		}
	}
	
	public enum AutoMode {
		TEST,
		TEST_LEFT,
		CENTER_GEAR,
		LEFT_GEAR,
		RIGHT_GEAR,
		BASELINE, 
		NOTHING;
	}
	
}
