package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

public class Drivetrain extends Subsystem1816 {

	private CANTalon frontRight, frontLeft, rearRight, rearLeft;
	private SlideDrive slideDrive;
	private Solenoid dropWheel, anchor;

	private double verticalStrafe, horizontalStrafe, rotation;

	private boolean slowMode;
	private static final double SLOW_MODE_SPEED = 0.75;

	public Drivetrain(int frontRight, int frontLeft, int middle, int rearRight, 
			int rearLeft, int pcmID, int dropDown, int anchor) {
		this.frontRight = new CANTalon(frontRight);
		this.frontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.frontRight.enableBrakeMode(true);
		this.frontRight.setVoltageRampRate(60);
		this.frontRight.reverseSensor(true);

		this.frontLeft = new CANTalon(frontLeft);
		this.frontLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.frontLeft.enableBrakeMode(true);
		this.frontLeft.setVoltageRampRate(60);
		this.frontLeft.reverseSensor(true);

		this.rearRight = new CANTalon(rearRight);
		this.rearRight.enableBrakeMode(true);
		this.rearRight.changeControlMode(TalonControlMode.Follower);
		this.rearRight.set(frontRight);

		this.rearLeft = new CANTalon(rearLeft);
		this.rearLeft.changeControlMode(TalonControlMode.Follower);
		this.rearLeft.set(frontLeft);
		this.rearLeft.enableBrakeMode(true);

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
	
	public Solenoid getDropWheel() {
		return dropWheel;
	}
	
	public CANTalon getFrontLeft() {
		return frontLeft;
	}
	
	public CANTalon getMiddle() {
		return slideDrive.getMiddle();
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
