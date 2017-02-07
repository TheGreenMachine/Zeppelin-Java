package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleDropWheelCommand extends Command {

	private Drivetrain drivetrain;

	public ToggleDropWheelCommand() {
		super("toggledropwheelcommand");
		drivetrain = Components.getInstance().drivetrain;
		requires(drivetrain);
	}

	@Override
	protected void initialize() {
		drivetrain.toggleDropWheel();
	}

	@Override
	protected void execute() {

	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {

	}

}
