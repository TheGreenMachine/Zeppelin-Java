package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

public class Drivetrain extends Subsystem1816 {

	private CANTalon frontRight, frontLeft, rearRight, rearLeft;
	private SlideDrive slideDrive;
	private Solenoid dropWheel, anchor;

	private double verticalStrafe, horizontalStrafe, rotation;

	private boolean slowMode;
	private static final double SLOW_MODE_SPEED = 0.50;
	private final int ENCODER_THRESHOLD = 25;

	public Drivetrain(int frontRight, int frontLeft, int middle, int rearRight, 
			int rearLeft, int pcmID, int dropDown, int anchor) {
		this.frontRight = new CANTalon(frontRight);
		this.frontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.frontRight.setVoltageRampRate(100);
		//this.frontRight.changeControlMode(CANTalon.TalonControlMode.Position);

		this.frontLeft = new CANTalon(frontLeft);
		this.frontLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.frontLeft.setInverted(true);
		this.frontLeft.setVoltageRampRate(100);
		//this.frontLeft.changeControlMode(CANTalon.TalonControlMode.Position);

		this.rearRight = new CANTalon(rearRight);
		this.rearRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.rearRight.setInverted(true);
		this.rearRight.setVoltageRampRate(100);

		this.rearLeft = new CANTalon(rearLeft);
		this.rearLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.rearLeft.setVoltageRampRate(100);

		slideDrive = new SlideDrive(this.frontLeft, this.frontRight, 
				this.rearLeft, this.rearRight, middle);
		
		this.dropWheel = new Solenoid(pcmID, dropDown);
		this.dropWheel.set(false);
		
		//this.anchor = new Solenoid(pcmID, anchor);
		//this.anchor.set(false);
	}

	@Override
	public void update() {
		if (slowMode) {
			verticalStrafe *= SLOW_MODE_SPEED;
			horizontalStrafe *= SLOW_MODE_SPEED;
			rotation *= SLOW_MODE_SPEED;
		}

		slideDrive.drive(verticalStrafe, horizontalStrafe, rotation);
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
		return Math.abs(frontLeft.get() - ticks) < ENCODER_THRESHOLD &&
				Math.abs(frontRight.get() - ticks) < ENCODER_THRESHOLD;
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
