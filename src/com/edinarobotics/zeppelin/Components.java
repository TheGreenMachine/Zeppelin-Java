package com.edinarobotics.zeppelin;

import com.edinarobotics.zeppelin.subsystems.Augers;
import com.edinarobotics.zeppelin.subsystems.Climber;
import com.edinarobotics.zeppelin.subsystems.Collector;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;
import com.edinarobotics.zeppelin.subsystems.Shooter;
import com.edinarobotics.zeppelin.subsystems.Vision;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;

public class Components {
   
	private static Components instance;
	
	public Drivetrain drivetrain;
	public Collector collector;
	public Shooter shooter;
	public Vision vision;
	public AHRS navX;
	public Compressor compressor;
	public Augers augers;
	public Climber climber;
	
	
	// CANTalon Constants
		// Drivetrain
		private static final int FRONT_LEFT = 2;
		private static final int FRONT_RIGHT = 8;
		private static final int REAR_LEFT = 1;
		private static final int REAR_RIGHT = 9;
		private static final int MIDDLE = 3;
		// End Drivetrain
		
		// Collector
		private static final int COLLECTOR = 5;
		// End Collector
		
		// Shooter
		private static final int LEFT_SHOOTER = 10;
		private static final int RIGHT_SHOOTER = 11;
		// End Shooter
		
		// Augers
		private static final int RIGHT_AUGER = 6;
		private static final int LEFT_AUGER = 7;
		// End Augers
		
		// Climber
		private static final int CLIMBER = 4;
		// End Climber
	// End CANTalon Constants
	
	// Pneumatic Constants
	private static final int PCM_ID = 10;
	private static final int DROP_WHEEL_ID = 7;
	private static final int ANCHOR_ID = 6;
	private static final int GEAR_COLLECTOR = 5;
	// End Pneumatic Constants
	
	// Encoder Constants
	private static final int SHOOTER_ENCODER_A = 0;
	private static final int SHOOTER_ENCODER_B = 1;
	// End Encoder Constants
	
	private Components() {
		drivetrain = new Drivetrain(FRONT_RIGHT, FRONT_LEFT, MIDDLE, REAR_RIGHT, 
				REAR_LEFT, PCM_ID, DROP_WHEEL_ID, ANCHOR_ID);
		
		navX = new AHRS(SPI.Port.kMXP);
		navX.reset();
		
		collector = new Collector(COLLECTOR, PCM_ID, GEAR_COLLECTOR);
		// shooter = new Shooter(SHOOTER);
		
		augers = new Augers(LEFT_AUGER, RIGHT_AUGER);
		
		climber = new Climber(CLIMBER);
		
		vision = new Vision(9600, SerialPort.Port.kMXP, 8,
				SerialPort.Parity.kNone, SerialPort.StopBits.kOne);
		
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
