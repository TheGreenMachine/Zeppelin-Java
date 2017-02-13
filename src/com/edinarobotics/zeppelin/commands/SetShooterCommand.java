package com.edinarobotics.zeppelin.commands;

import com.ctre.CANTalon;
import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class SetShooterCommand extends Command {

	private Shooter shooter;
	private int target;

	private static final double TARGET_SPEED = .9;

	public SetShooterCommand(int target) {
		super("setshootercommand");
		this.target = target;
		shooter = Components.getInstance().shooter;
		requires(shooter);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		CANTalon talon = shooter.getShooter();

		if (talon.getSpeed() < target)
			talon.set(TARGET_SPEED);
		else
			talon.set(0.0);
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
