package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;
import com.edinarobotics.zeppelin.subsystems.Vision;

import edu.wpi.first.wpilibj.command.Command;

public class RunVisionVerticalCommand extends Command {
	
	private Drivetrain drivetrain;
	private Vision vision;
	
	public RunVisionVerticalCommand() {
		super("runvisionverticalcommand");
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
		vision.runVerticalStrafe();
	}

	@Override
	protected boolean isFinished() {
		return vision.isYAtTarget();
	}

	@Override
	protected void end() {
		drivetrain.setDrivetrain(0.0, 0.0, 0.0);;
	}

	@Override
	protected void interrupted() {
		end();
	}

}
