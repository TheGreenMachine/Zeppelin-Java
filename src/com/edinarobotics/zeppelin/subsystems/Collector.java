package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.edinarobotics.utils.subsystems.Subsystem1816;

public class Collector extends Subsystem1816 {

	private CANTalon collector;
	private CollectorState state;

	public Collector(int collector) {
		this.collector = new CANTalon(collector);
		this.collector.setInverted(true);
		state = CollectorState.OFF;
	}

	public void setCollectorState(CollectorState state) {
		this.state = state;
		update();
	}

	@Override
	public void update() {
		collector.set(state.getSpeed());
	}

	public enum CollectorState {
		OFF(0.0), 
		LOW(0.5), 
		HIGH(1.0);

		private double speed;

		CollectorState(double speed) {
			this.speed = speed;
		}

		public double getSpeed() {
			return speed;
		}
	}

}
