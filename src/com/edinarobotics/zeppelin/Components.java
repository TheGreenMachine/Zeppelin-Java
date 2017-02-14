package com.edinarobotics.zeppelin;

import com.edinarobotics.zeppelin.subsystems.Collector;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;
import com.edinarobotics.zeppelin.subsystems.Shooter;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;

public class Components {
   
	private static Components instance;
	
	public Drivetrain drivetrain;
	public Collector collector;
	public Shooter shooter;
	public AHRS navX;
	public Compressor compressor;

	public Encoder rearRightEncoder;
	
	// CANTalon Constants
		// Drivetrain
		private static final int FRONT_LEFT = 4;
		private static final int FRONT_RIGHT = 5;
		private static final int REAR_LEFT = 3;
		private static final int REAR_RIGHT = 6;
		private static final int MIDDLE = 7;
		// End Drivetrain
		
		// Collector
		private static final int COLLECTOR = 2;
		// End Collector
		
		// Shooter
		private static final int SHOOTER = 1;
		// End Shooter
	// End CANTalon Constants
	
	// Pneumatic Constants
	private static final int PCM_ID = 10;
	private static final int DROP_WHEEL_ID = 0;
	private static final int ANCHOR_ID = 1;
	// End Pneumatic Constants
	
	// Encoder Constants
	private static final int SHOOTER_ENCODER_A = 0;
	private static final int SHOOTER_ENCODER_B = 1;
	// End Encoder Constants
	
	private Components() {
		drivetrain = new Drivetrain(FRONT_RIGHT, FRONT_LEFT, MIDDLE, REAR_RIGHT, 
				REAR_LEFT, PCM_ID, DROP_WHEEL_ID, ANCHOR_ID);
		navX = new AHRS(SPI.Port.kMXP);
		collector = new Collector(COLLECTOR);
		
		shooter = new Shooter(SHOOTER, SHOOTER_ENCODER_A, SHOOTER_ENCODER_B);
		
		compressor = new Compressor(PCM_ID);
		compressor.start();
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
