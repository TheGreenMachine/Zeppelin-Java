package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

public class DriveXInchesVerticalCommand extends Command {

	private Drivetrain drivetrain;
	private int inches;
	private int ticks;
	private final int CONVERSION_FACTOR = 39;
	private final int TARGET_THRESHOLD = 5;

	private AHRS gyro;
	private double heading;

	public DriveXInchesVerticalCommand(int inches) {
		super("drivexinchesverticalcommand");
		drivetrain = Components.getInstance().drivetrain;
		gyro = Components.getInstance().navX;
		this.inches = inches;
		ticks = (inches * CONVERSION_FACTOR) + drivetrain.getFrontLeft().getEncPosition();
		requires(drivetrain);
	}

	@Override
	protected void initialize() {
		heading = gyro.getAngle();
		System.out.println("Initializing...");
	}

	@Override
	protected void execute() {
		int currentLeftValue = drivetrain.getFrontLeft().getEncPosition();
		double currentHeading = gyro.getAngle();

		if (ticks > currentLeftValue) {
			if (currentLeftValue > (ticks - 1000)) {

				if (Math.abs(currentHeading - heading) > 0.3) {
					drivetrain.setDrivetrain(0.30, 0.0, -(currentHeading - heading) * .2);
				} else {
					drivetrain.setDrivetrain(0.30, 0, 0);
				}
				System.out.println("1");
			} else {
				if (Math.abs(currentHeading - heading) > 0.3) {
					drivetrain.setDrivetrain(0.70, 0, -(currentHeading - heading) * .1);
				} else {
					drivetrain.setDrivetrain(0.70, 0, 0);
				}
				System.out.println("2");
			}
		} else {
			if (currentLeftValue < (ticks + 1000)) {
				if (Math.abs(currentHeading - heading) > 0.3) {
					drivetrain.setDrivetrain(-0.30, 0, (currentHeading - heading) * .1);
				} else {
					drivetrain.setDrivetrain(-0.30, 0, 0);
				}
				System.out.println("3");
			} else {
				if (Math.abs(currentHeading - heading) > 0.3) {
					drivetrain.setDrivetrain(-0.70, 0, (currentHeading - heading) * .1);
				} else {
					drivetrain.setDrivetrain(-0.70, 0, 0);
				}
				System.out.println("4");
			}
		}

		System.out.println("Left value: " + currentLeftValue + "; Left target: " + ticks);
		System.out.println("Current heading: " + currentHeading + "; Target heading: " + heading);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(drivetrain.getFrontLeft().getEncPosition() - ticks) < TARGET_THRESHOLD;
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
