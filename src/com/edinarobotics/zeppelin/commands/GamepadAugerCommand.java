package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Augers;

import edu.wpi.first.wpilibj.command.Command;

public class GamepadAugerCommand extends Command {

	private Augers augers;
	private Gamepad gamepad;
	
	public GamepadAugerCommand(Gamepad gamepad) {
		super("gamepadaugercommand");
		this.gamepad = gamepad;
		augers = Components.getInstance().augers;
		requires(augers);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		double augervalue = gamepad.getLeftY();
		augers.setSpeed(augervalue*.6);
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
