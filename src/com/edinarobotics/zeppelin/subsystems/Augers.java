package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.command.Command;

public class Augers extends Subsystem1816{

	private CANTalon left, right;
	private double augerSpeed = 0;
	private boolean autoRunning = false;
	
	public Augers(int left, int right) {
		this.left = new CANTalon(left);
		this.right = new CANTalon(right);
	}
	
	@Override
	public void update() {
		left.set(augerSpeed);
		right.set(augerSpeed);
	}
	
	public void setSpeed(double speed) {
		augerSpeed = speed;
		update();
	}
	
	public void setAutoRunning(boolean value) {
		this.autoRunning = value;
	}
	
	public boolean isAutoRunning() {
		return autoRunning;
	}
	
	public void setDefaultCommand(Command command) {
		if (getDefaultCommand() != null) {
			super.getDefaultCommand().cancel();
		}
		super.setDefaultCommand(command);
	}

}