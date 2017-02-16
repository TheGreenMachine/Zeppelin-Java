package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class GamepadDriveCommand extends Command {

	private Drivetrain drivetrain;
	private Gamepad gamepad0;

	public GamepadDriveCommand(Gamepad gamepad0) {
		super("gamepaddrivecommand");
		this.gamepad0 = gamepad0;
		drivetrain = Components.getInstance().drivetrain;
		requires(drivetrain);
	}

	protected void initialize() {

	}

	protected void execute() {
		double verticalStrafe = gamepad0.getLeftY();
		double horizontalStrafe = gamepad0.getLeftX();
		double rotation = gamepad0.getRightX();

		drivetrain.setDrivetrain(verticalStrafe, horizontalStrafe, rotation);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		drivetrain.setDrivetrain(0, 0, 0);
	}

	protected void interrupted() {
		end();
	}
}
