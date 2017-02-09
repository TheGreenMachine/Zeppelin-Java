package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveXInchesCommand extends Command {

	private Drivetrain drivetrain;
	private int inches;

	public DriveXInchesCommand(int inches) {
		super("drivexinchescommand");
		this.inches = inches;
		drivetrain = Components.getInstance().drivetrain;
		requires(drivetrain);
	}

	@Override
	protected void initialize() {
		drivetrain.getFrontLeft().setPosition(0);
		drivetrain.getFrontRight().setPosition(0);
	}

	@Override
	protected void execute() {
		drivetrain.getFrontLeft().set(-inches);
		drivetrain.getFrontRight().set(inches);
		
		SmartDashboard.putNumber("Left encoder value: ", -drivetrain.getFrontLeft().getPosition());
		SmartDashboard.putNumber("Right encoder value: ", drivetrain.getFrontLeft().getPosition());
	}

	@Override
	protected boolean isFinished() {
		return drivetrain.getFrontLeft().getPosition() == -inches && drivetrain.getFrontRight().getPosition() == inches;
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
