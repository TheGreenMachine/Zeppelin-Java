package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class GamepadClimberCommand extends Command {

	private Climber climber;
	private Gamepad gamepad;
	
	public GamepadClimberCommand(Gamepad gamepad) {
		super("gamepadclimbercommand");
		this.gamepad = gamepad;
		climber = Components.getInstance().climber;
		requires(climber);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		double climbervalue = gamepad.getRightY();
		climber.setSpeed(climbervalue);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		
	}
	
	@Override
	protected void interrupted() {
		
	}
}
