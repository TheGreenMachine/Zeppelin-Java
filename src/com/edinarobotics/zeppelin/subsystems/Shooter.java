package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.Encoder;

public class Shooter extends Subsystem1816 {

	private CANTalon shooterTalon;
	private Encoder encoder;
	private ShooterSpeed shooterSpeed;
	
	private static final double P = 0.01300;
	private static final double I = 0.00012;
	private static final double D = 2.00000;
	private static final double F = 0.00000;
	
	public Shooter(int shooterTalon, int encoderA, int encoderB) {
		this.shooterTalon = new CANTalon(shooterTalon);
		this.shooterTalon.changeControlMode(TalonControlMode.Speed);
		this.shooterTalon.setFeedbackDevice(FeedbackDevice.valueOf(encoder.get()));
		this.shooterTalon.setPID(P, I, D);
		this.shooterTalon.setF(F);
		
		encoder = new Encoder(encoderA, encoderB);
	}

	@Override
	public void update() {
		shooterTalon.set(shooterSpeed.getSpeed());
	}
	
	public void setSpeed(ShooterSpeed speed) {
		shooterSpeed = speed;
		update();
	}
	
	public enum ShooterSpeed {
		ON(3350), 
		OFF(0);
		
		int speed;
		
		ShooterSpeed(int speed) {
			this.speed = speed;
		}
		
		public int getSpeed() {
			return speed;
		}
	}
}
