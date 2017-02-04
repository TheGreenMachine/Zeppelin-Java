package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveXInchesCommand extends Command {
	
	private Drivetrain drivetrain;
	private int inches;

	private final double CONVERSION_RATE = 15.916;
	
	public DriveXInchesCommand(int inches) {
		super("drivexinchescommand");
		this.inches = inches;
		drivetrain = Components.getInstance().drivetrain;
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
		drivetrain.setPosition((int) (inches * CONVERSION_RATE));
	}

	@Override
	protected void execute() {
	}
	
	@Override
	protected boolean isFinished() {
		return drivetrain.isOnTarget((int) (inches * CONVERSION_RATE));
	}

	@Override
	protected void end() {
		drivetrain.setDrivetrain(0, 0, 0);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
