package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

public class SlideDrive extends RobotDrive {

	private CANTalon middle;

	public SlideDrive(SpeedController frontLeftMotor, SpeedController frontRightMotor, 
			int middleMotor) {
		super(frontLeftMotor, frontRightMotor);

		middle = new CANTalon(middleMotor);
		middle.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		middle.enableBrakeMode(true);
		middle.setVoltageRampRate(60);
		middle.setInverted(true);
	}

	public void drive(double verticalStrafe, double horizontalStrafe, double rotation) {
		arcadeDrive(verticalStrafe, rotation);
		middle.set(horizontalStrafe);
	}
	
	public CANTalon getMiddle() { 
		return middle;
	}

}
