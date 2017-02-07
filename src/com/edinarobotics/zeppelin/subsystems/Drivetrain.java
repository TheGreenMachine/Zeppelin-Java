package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.edinarobotics.utils.rate.RampRateHelper;
import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem1816 {

	private CANTalon frontRight, frontLeft, rearRight, rearLeft;
	private SlideDrive slideDrive;
	private Solenoid dropWheel, anchor;

	private double verticalStrafe, horizontalStrafe, rotation;

	private boolean slowMode;
	private static final double SLOW_MODE_SPEED = 0.50;
	private final int ENCODER_THRESHOLD = 25;

	private RampRateHelper ramp;

	public Drivetrain(int frontRight, int frontLeft, int middle, int rearRight, 
			int rearLeft, int pcmID, int dropDown, int anchor) {
		ramp = new RampRateHelper(0.7, true, false);

		this.frontRight = new CANTalon(frontRight);
		this.frontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		this.frontLeft = new CANTalon(frontLeft);
		this.frontLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		this.rearRight = new CANTalon(rearRight);
		this.rearRight.changeControlMode(TalonControlMode.Follower);
		this.rearRight.set(frontRight);

		this.rearLeft = new CANTalon(rearLeft);
		this.rearLeft.changeControlMode(TalonControlMode.Follower);
		this.rearLeft.set(frontLeft);

		slideDrive = new SlideDrive(this.frontLeft, this.frontRight, middle);

		this.dropWheel = new Solenoid(pcmID, dropDown);
		this.dropWheel.set(false);

		this.anchor = new Solenoid(pcmID, anchor);
		this.anchor.set(false);
	}

	@Override
	public void update() {
		if (slowMode) {
			verticalStrafe *= SLOW_MODE_SPEED;
			horizontalStrafe *= SLOW_MODE_SPEED;
			rotation *= SLOW_MODE_SPEED;
		}

		slideDrive.drive(-verticalStrafe, horizontalStrafe, rotation);
		
		SmartDashboard.putNumber("Left encoder value: ", frontLeft.getEncPosition());
		SmartDashboard.putNumber("Right encoder value: ", frontRight.getEncPosition());
	}

	public void setDrivetrain(double verticalStrafe, double horizontalStrafe, double rotation) {
		this.verticalStrafe = verticalStrafe;
		this.horizontalStrafe = horizontalStrafe;
		this.rotation = rotation;

		update();
	}

	public void toggleAnchor() {
		anchor.set(!anchor.get());
	}

	public void toggleDropWheel() {
		dropWheel.set(!dropWheel.get());
	}

	public void setPosition(int ticks) {
		frontLeft.setPosition(0.0);
		frontRight.setPosition(0.0);

		frontLeft.set(ticks);
		frontRight.set(ticks);
	}

	public boolean isOnTarget(int ticks) {
		return Math.abs(frontLeft.get() - ticks) < ENCODER_THRESHOLD
				&& Math.abs(frontRight.get() - ticks) < ENCODER_THRESHOLD;
	}

	public CANTalon getFrontRight() {
		return frontRight;
	}

	public void setFrontRight(CANTalon frontRight) {
		this.frontRight = frontRight;
	}

	public CANTalon getFrontLeft() {
		return frontLeft;
	}

	public void setFrontLeft(CANTalon frontLeft) {
		this.frontLeft = frontLeft;
	}

	public void setDefaultCommand(Command command) {
		if (getDefaultCommand() != null) {
			super.getDefaultCommand().cancel();
		}
		super.setDefaultCommand(command);
	}

	public double getVerticalStrafe() {
		return verticalStrafe;
	}

	public void setVerticalStrafe(double verticalStrafe) {
		this.verticalStrafe = verticalStrafe;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public double getHorizontalStrafe() {
		return horizontalStrafe;
	}

	public void setHorizontalStrafe(double horizontalStrafe) {
		this.horizontalStrafe = horizontalStrafe;
	}

	public void setSlowMode(boolean slowMode) {
		this.slowMode = slowMode;
		update();
	}

}
