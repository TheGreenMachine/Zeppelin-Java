package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.Encoder;

public class Shooter extends Subsystem1816 {

	private CANTalon leftShooter, rightShooter;
	private ShooterSpeed shooterSpeed;
	private boolean autoRunning = false;

	private static final double P = 0.01300;
	private static final double I = 0.00012;
	private static final double D = 2.00000;
	private static final double F = 0.00000;
	
	public Shooter(int leftShooter, int rightShooter) {
		this.rightShooter = new CANTalon(rightShooter);
		this.rightShooter.changeControlMode(TalonControlMode.Speed);
		this.rightShooter.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		this.rightShooter.setPID(P, I, D);
		this.rightShooter.setF(F);
		
		this.leftShooter = new CANTalon(leftShooter);
		this.leftShooter.changeControlMode(TalonControlMode.Follower);
		this.leftShooter.set(rightShooter);
	}

	@Override
	public void update() {
		rightShooter.set(shooterSpeed.getSpeed());
	}

	public void setSpeed(ShooterSpeed speed) {
		shooterSpeed = speed;
		update();
	}
	
	public void setAutoRunning(boolean value) {
		autoRunning = value;
	}
	
	public boolean isAutoRunning() {
		return autoRunning;
	}

	public enum ShooterSpeed {
		ON(3350), OFF(0);

		int speed;

		ShooterSpeed(int speed) {
			this.speed = speed;
		}

		public int getSpeed() {
			return speed;
		}
	}
}
