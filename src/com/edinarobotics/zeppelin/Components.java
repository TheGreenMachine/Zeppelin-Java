package com.edinarobotics.zeppelin;

import com.edinarobotics.zeppelin.subsystems.Drivetrain;

public class Components {
   
	private static Components instance;
	
	public Drivetrain drivetrain;
	
	private static final int FRONT_LEFT = 1;
	private static final int FRONT_RIGHT = 2;
	private static final int REAR_LEFT = 3;
	private static final int REAR_RIGHT = 4;
	private static final int MIDDLE = 5;
	
	
	private Components() {
		drivetrain = new Drivetrain(FRONT_RIGHT, FRONT_LEFT, MIDDLE, REAR_RIGHT, REAR_LEFT);
		
	}
	
	/**
	 * Returns the proper instance of Components. This method creates a new
	 * Components object the first time it is called and returns that object for
	 * each subsequent call.
	 *
	 * @return The current instance of Components.
	 */
	public static Components getInstance() {
		if (instance == null) {
			instance = new Components();
		}
		
		return instance;
	}
	
}
