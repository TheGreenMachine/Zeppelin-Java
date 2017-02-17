package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;
import com.edinarobotics.zeppelin.subsystems.Vision;

import edu.wpi.first.wpilibj.command.Command;

public class RunVisionApproachCommand extends Command{
	
	private Drivetrain drivetrain;
	private Vision vision;
	
	public RunVisionApproachCommand() {
		super("runvisionapproachcommand");
		drivetrain = Components.getInstance().drivetrain;
		vision = Components.getInstance().vision;
		requires(vision);
		requires(drivetrain);
	}

	@Override
	protected void initialize() {
		
	}
	
	@Override
	protected void execute() {
		vision.runApproach();
	}

	@Override
	protected boolean isFinished() {
		return vision.isXAtTarget() && vision.isYAtTarget();
	}

	@Override
	protected void end() {
		drivetrain.setDrivetrain(0.0, 0.0, 0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
