package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleSlowModeCommand extends Command {

	private Drivetrain drivetrain;
	private boolean slowMode;

	public ToggleSlowModeCommand(boolean slowMode) {
		super("toggleslowmodecommand");
		drivetrain = Components.getInstance().drivetrain;
		this.slowMode = slowMode;
		requires(drivetrain);
	}

	protected void initialize() {
		drivetrain.setSlowMode(slowMode);
	}

	protected void execute() {

	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {

	}

	protected void interrupted() {

	}
}
