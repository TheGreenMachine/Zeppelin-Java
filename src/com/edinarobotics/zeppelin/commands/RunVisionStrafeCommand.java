package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;
import com.edinarobotics.zeppelin.subsystems.Vision;

import edu.wpi.first.wpilibj.command.Command;

public class RunVisionStrafeCommand extends Command {
	
	private Vision vision;
	private Drivetrain drivetrain;
	
	public RunVisionStrafeCommand() {
		super("runvisionstrafecommand");
		vision = Components.getInstance().vision;
		drivetrain = Components.getInstance().drivetrain;
		requires(vision);
		requires(drivetrain);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		vision.runHorizontalStrafe();		
	}

	@Override
	protected boolean isFinished() {
		return vision.isXAtTarget();
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
