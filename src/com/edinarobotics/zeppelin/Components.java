package com.edinarobotics.zeppelin;

import com.edinarobotics.zeppelin.subsystems.Drivetrain;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C.Port;

public class Components {
   
	private static Components instance;
	
	public Drivetrain drivetrain;
	public AHRS navX;
	
	private static final int FRONT_LEFT = 4;
	private static final int FRONT_RIGHT = 5;
	private static final int REAR_LEFT = 3;
	private static final int REAR_RIGHT = 6;
	private static final int MIDDLE = 7;
	
	
	private Components() {
		drivetrain = new Drivetrain(FRONT_RIGHT, FRONT_LEFT, MIDDLE, REAR_RIGHT, REAR_LEFT);
		navX = new AHRS(Port.kMXP);
		
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
