package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.command.Command;

public class Climber extends Subsystem1816 {
	
	private CANTalon climb;
	private double speed;
	
	public Climber(int climb) {
		this.climb = new CANTalon(climb);
	}
	
	@Override
	public void update() {
		climb.set(speed);
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
		update();
	}
	
	public void setDefaultCommand(Command command) {
		if (getDefaultCommand() != null) {
			super.getDefaultCommand().cancel();
		}
		super.setDefaultCommand(command);
	}

}
