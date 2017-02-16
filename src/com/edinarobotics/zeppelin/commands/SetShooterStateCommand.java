package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Shooter;
import com.edinarobotics.zeppelin.subsystems.Shooter.ShooterSpeed;

import edu.wpi.first.wpilibj.command.Command;

public class SetShooterStateCommand extends Command {

	private Shooter shooter;
	private ShooterSpeed shooterSpeed;

	public SetShooterStateCommand(ShooterSpeed shooterSpeed) {
		super("setshooterstatecommand");
		shooter = Components.getInstance().shooter;
		this.shooterSpeed = shooterSpeed;
		requires(shooter);
	}

	@Override
	protected void initialize() {
		shooter.setSpeed(shooterSpeed);
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
