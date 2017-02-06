package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem1816 {

	private CANTalon shooter;
	private double speed;

	private final double P = 0.00005;
	private final double I = 0.0;
	private final double D = 0.0;
	
	private static final double TARGET_SPEED = .9;
	private static final int TARGET_RPM = 25000;
	
	private boolean active = false;
	
	public Shooter(int shooter) {

		this.shooter = new CANTalon(shooter);
		//this.shooter.changeControlMode(TalonControlMode.Speed);
		this.shooter.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		//this.shooter.setPID(P, I, D);
		//this.shooter.configNominalOutputVoltage(+0.0f, -0.0f);
		//this.shooter.configPeakOutputVoltage(+12.0f, -12.0f);

	}

	public void setSpeed(double speed) {
		this.speed = speed;
		update();
	}
	
	public CANTalon getShooter() {
		return shooter;
	}

	@Override
	public void update() {
		
		if(active){
			if(shooter.getSpeed()<TARGET_RPM){
				shooter.set(TARGET_SPEED);
			} else {
				shooter.set(0.0);
			}
		} else{
			shooter.set(0.0);
		}
		
		SmartDashboard.putNumber("Shooter Speed", shooter.getSpeed());
	}

}
