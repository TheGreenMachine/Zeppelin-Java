package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.utils.log.Logging;
import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

public class DriveXInchesVerticalCommand extends Command {

	private Drivetrain drivetrain;
	private int inches;
	private double speed;
	private int ticks;
	private final int CONVERSION_FACTOR = 34;
	private final int TARGET_THRESHOLD = 5;

	private AHRS gyro;
	private double heading;
	
	private Logging driveLog;
	private Logging commonLog;

	public DriveXInchesVerticalCommand(double speed, int inches) {
		super("drivexinchesverticalcommand");
		drivetrain = Components.getInstance().drivetrain;
		gyro = Components.getInstance().navX;
		this.inches = inches;
		this.speed = speed;
		requires(drivetrain);
	}

	@Override
	protected void initialize() {
		heading = gyro.getAngle();
		System.out.println("Initializing...");	
		ticks = (inches * CONVERSION_FACTOR) + drivetrain.getFrontLeft().getEncPosition();
		driveLog = new Logging("DriveXInchesCommand - " + System.currentTimeMillis());
		commonLog = new Logging("DriveXInchesCommand-Common - " + System.currentTimeMillis());
		driveLog.log("ticks, targetTicks, voltage, currentHeading, targetHeading");
	}

	@Override
	protected void execute() {
		double currentLeftValue = drivetrain.getFrontLeft().getEncPosition();
		double currentHeading = gyro.getAngle();

		if (ticks > currentLeftValue) {
			if (currentLeftValue > (ticks - 1000)) {

				if (Math.abs(currentHeading - heading) > 2) {
					drivetrain.setVerticalStrafe(speed/2);
					drivetrain.setRotation(-(currentHeading - heading) * .03);
					System.out.println("1.2");
				} else {
					drivetrain.setVerticalStrafe(speed/2);
					System.out.println("1.1");
				}
				System.out.println("1");
			} else {
				if (Math.abs(currentHeading - heading) > 2) {
					drivetrain.setVerticalStrafe(speed);
					drivetrain.setRotation(-(currentHeading - heading) * .03);
				} else {
					drivetrain.setVerticalStrafe(speed);
				}
				System.out.println("2");
			}
		} else {
			if (currentLeftValue < (ticks + 1000)) {
				if (Math.abs(currentHeading - heading) > 2) {
					drivetrain.setVerticalStrafe(-speed/2);
					drivetrain.setRotation(-(currentHeading - heading) * .03);
				} else {
					drivetrain.setVerticalStrafe(-speed/2);
				}
				System.out.println("3");
			} else {
				if (Math.abs(currentHeading - heading) > 2) {
					drivetrain.setVerticalStrafe(-speed);
					drivetrain.setRotation(-(currentHeading - heading) * .03);
				} else {
					drivetrain.setVerticalStrafe(-speed);
				}
				System.out.println("4");
			}
		}

		driveLog.log(currentLeftValue + ", " + ticks + ", " + drivetrain.getFrontLeft().get() + ", " + currentHeading + ", " + heading);
		
		System.out.println("Left value: " + currentLeftValue + "; Left target: " + ticks);
		System.out.println("Current heading: " + currentHeading + "; Target heading: " + heading);
	}

	@Override
	protected boolean isFinished() {
		if (Math.abs(drivetrain.getFrontLeft().getEncPosition() - ticks) < TARGET_THRESHOLD)
			System.out.println("Finished");
			
		return Math.abs(drivetrain.getFrontLeft().getEncPosition() - ticks) < TARGET_THRESHOLD;
	
	}

	@Override
	protected void end() {
		drivetrain.setDrivetrain(0.0, 0.0, 0.0);
		driveLog.close();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
