package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.Solenoid;

public class Collector extends Subsystem1816 {

	private CANTalon collector;
	private CollectorState state;
	private Solenoid solenoid;

	public Collector(int collector, int pcmID, int solenoidPort) {
		this.collector = new CANTalon(collector);
		this.collector.setInverted(true);
		this.solenoid = new Solenoid(pcmID, solenoidPort);
		this.solenoid.set(false);
		state = CollectorState.OFF;
	}

	public void setCollectorState(CollectorState state) {
		this.state = state;
		update();
	}
	
	public void actuateCollector(boolean value) {
		solenoid.set(value);
	}

	@Override
	public void update() {
		collector.set(state.getSpeed());
	}

	public enum CollectorState {
		OFF(0.0), 
		FORWARDS(1.0),
		REVERSE(-1.0);
		
		private double speed;

		CollectorState(double speed) {
			this.speed = speed;
		}

		public double getSpeed() {
			return speed;
		}
	}

}
