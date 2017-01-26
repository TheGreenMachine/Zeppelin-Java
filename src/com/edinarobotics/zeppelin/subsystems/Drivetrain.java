package com.edinarobotics.zeppelin.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.command.Command;

public class Drivetrain extends Subsystem1816 {

	private CANTalon frontRight, frontLeft, rearRight, rearLeft;
	private SlideDrive slideDrive;
	
	private double verticalStrafe, horizontalStrafe, rotation;
	
	public Drivetrain(int frontRight, int frontLeft, int middle, int rearRight, 
			int rearLeft) {
		this.frontRight = new CANTalon(frontRight);
		this.frontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.frontRight.setVoltageRampRate(100);
		
		this.frontLeft = new CANTalon(frontLeft);
		this.frontLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.frontLeft.setInverted(true);
		this.frontLeft.setVoltageRampRate(100);
		
		this.rearRight = new CANTalon(rearRight);
		this.rearRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.rearRight.setInverted(true);
		this.rearRight.setVoltageRampRate(100);
		
		this.rearLeft = new CANTalon(rearLeft);
		this.rearLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.rearLeft.setVoltageRampRate(100);
		
		slideDrive = new SlideDrive(this.frontLeft, this.frontRight, this.rearLeft, 
				this.rearRight, middle);				
	}
	
	@Override
	public void update() {
		slideDrive.drive(verticalStrafe, horizontalStrafe, rotation);		
	}
	 
	public void setDrivetrain(double verticalStrafe, double horizontalStrafe, 
			double rotation){
		this.verticalStrafe = verticalStrafe;
		this.horizontalStrafe = horizontalStrafe;
		this.rotation = rotation;
		
		update();
	}
	
	public CANTalon getRearRight() {
		return rearRight;
	}

	public void setRearRight(CANTalon rearRight) {
		this.rearRight = rearRight;
	}

	public CANTalon getRearLeft() {
		return rearLeft;
	}

	public void setRearLeft(CANTalon rearLeft) {
		this.rearLeft = rearLeft;
	}

	public void setDefaultCommand(Command command){
		if(getDefaultCommand() != null){
			super.getDefaultCommand().cancel();
		}
		super.setDefaultCommand(command);
	}

	public double getVerticalStrafe() {
		return verticalStrafe;
	}

	public void setVerticalStrafe(double verticalStrafe) {
		this.verticalStrafe = verticalStrafe;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public double getHorizontalStrafe() {
		return horizontalStrafe;
	}

	public void setHorizontalStrafe(double horizontalStrafe) {
		this.horizontalStrafe = horizontalStrafe;
	}

}
