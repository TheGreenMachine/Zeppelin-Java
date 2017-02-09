package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.edinarobotics.utils.pid.PIDConfig;
import com.edinarobotics.utils.pid.PIDTuningManager;
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
	
	private static final double kP = 0.50;
	private static final double kI = 0.00;
	private static final double kD = 0.00;

	public Drivetrain(int frontRight, int frontLeft, int middle, int rearRight, 
			int rearLeft, int pcmID, int dropDown, int anchor) {
		ramp = new RampRateHelper(0.7, true, false);

		this.frontRight = new CANTalon(frontRight);
		this.frontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.frontRight.setPID(kP, kI, kD);
		this.frontRight.configEncoderCodesPerRev(10);
		this.frontRight.enableBrakeMode(true);
		this.frontRight.setVoltageRampRate(100);
		this.frontRight.changeControlMode(TalonControlMode.Position);
		this.frontRight.reverseSensor(true);
		this.frontRight.setPosition(0);

		this.frontLeft = new CANTalon(frontLeft);
		this.frontLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.frontLeft.setPID(kP, kI, kD);
		this.frontLeft.configEncoderCodesPerRev(10);
		this.frontLeft.enableBrakeMode(true);
		this.frontLeft.setVoltageRampRate(100);
		this.frontLeft.changeControlMode(TalonControlMode.Position);
		this.frontLeft.reverseSensor(true);
		this.frontLeft.setPosition(0);

		this.rearRight = new CANTalon(rearRight);
		this.rearRight.changeControlMode(TalonControlMode.Follower);
		this.rearRight.set(frontRight);
		this.rearRight.enableBrakeMode(true);

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
		
		SmartDashboard.putNumber("Left encoder value: ", frontLeft.getPosition());
		SmartDashboard.putNumber("Right encoder value: ", frontRight.getPosition());
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

	public void setPosition() {
		frontLeft.setPosition(0.0);
		frontRight.setPosition(0.0);
	}
	
	public void setTarget(int ticks) {
		frontLeft.enable();
		frontRight.enable();
		
		frontLeft.set(ticks);
		frontRight.set(ticks);
		
	}
	
	public void changeControlMode(TalonControlMode mode) {
		frontLeft.changeControlMode(mode);
		frontRight.changeControlMode(mode);
	}

	public boolean isOnTarget(int ticks) {
		return Math.abs(frontLeft.get() - ticks) < ENCODER_THRESHOLD
				&& Math.abs(frontRight.get() - ticks) < ENCODER_THRESHOLD;
	}
	
	public CANTalon getFrontRight() {
		return frontRight;
	}
	
	public CANTalon getFrontLeft() {
		return frontLeft;
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
