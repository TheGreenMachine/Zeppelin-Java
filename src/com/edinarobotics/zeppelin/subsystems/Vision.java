package com.edinarobotics.zeppelin.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;
import com.edinarobotics.zeppelin.Components;

import edu.wpi.first.wpilibj.SerialPort;

public class Vision extends Subsystem1816 {

	private SerialPort serial;

	private double verticalStrafe;
	private double horizontalStrafe;

	private final int CAMERA_WIDTH = 800;

	private final int X_SLOW_RANGE = 60;
	private final int X_ENDING_TOLERANCE = 3;

	private String kInput;
	private double kX;
	private double kY;
	private double area;

	public Vision(int baudRate, SerialPort.Port port, int bytes, SerialPort.Parity parity,
			SerialPort.StopBits stopBits) {
		serial = new SerialPort(baudRate, port, bytes, parity, stopBits);
	}

	@Override
	public void update() {
		Components.getInstance().drivetrain.setHorizontalStrafe(horizontalStrafe);
	}
	
	public void runApproach(){
		readInput();
		calculateHorizontalStrafe();
		update();
	}

	public void runHorizontalStrafe() {
		readInput();
		double deltaVision = CAMERA_WIDTH / 2 - kX;

		double strafe;

		if (deltaVision < X_SLOW_RANGE && deltaVision > -X_SLOW_RANGE) {
			if (deltaVision > X_ENDING_TOLERANCE) {
				strafe = -0.38 / 1.6;
				System.out.println("1");
			} else if (deltaVision < -3) {
				strafe = 0.38 / 1.6;
				System.out.println("2");
			} else {
				strafe = 0;
				System.out.println("3");
			}
		} else {
			if (deltaVision > 0) {
				strafe = -0.38;
				System.out.println("4");
			} else {
				strafe = 0.38;
				System.out.println("5");
			}
		}

		horizontalStrafe = strafe;
		update();
	}
	
	public void calculateHorizontalStrafe() {
		double deltaVision = CAMERA_WIDTH / 2 - kX;

		double strafe;

		if (deltaVision < X_SLOW_RANGE && deltaVision > -X_SLOW_RANGE) {
			if (deltaVision > X_ENDING_TOLERANCE)
				strafe = 0.5 / 1.5;
			else if (deltaVision < -3)
				strafe = -0.5 / 1.5;
			else
				strafe = 0;
		} else {
			if (deltaVision > 0)
				strafe = 0.5;
			else
				strafe = -0.5;
		}

		horizontalStrafe = strafe;
	}

	public boolean isXAtTarget() {
		return kX > (CAMERA_WIDTH / 2 - X_ENDING_TOLERANCE) && kX < 
				(CAMERA_WIDTH / 2 + X_ENDING_TOLERANCE);
	}

	public void readInput() {
		int space1 = 0;
		int space2 = 0;
		int endBracket = 0;

		try {
			
			String input = serial.readString();

			if (input.length() > 0) {
				if (input.startsWith("{") && input.endsWith("}")) {
					space1 = input.indexOf(' ');
					space2 = input.substring(space1 + 1).indexOf(' ') + space1 + 1;
					endBracket = input.indexOf('}');

					if (space1 != 1) {
						kX = Integer.parseInt(input.substring(1, space1), 10);
						kY = Integer.parseInt(input.substring(space1 + 1, space2), 10);
						area = Integer.parseInt(input.substring(space2 + 1, endBracket), 10);
					} else if (input.substring(0, 1).equals("{")) {
						kInput = input;
					} else if (input.substring(input.length() - 1, input.length()).equals("}")) {
						kInput += input;
						if (kInput.substring(0, 1).equals("{")
								&& kInput.substring(kInput.length() - 1, kInput.length()).equals("}")) {
							space1 = kInput.indexOf(' ');
							space2 = kInput.substring(space1 + 1).indexOf(' ') + space1 + 1;
							if (space1 != 1) {
								kX = Integer.parseInt(kInput.substring(1, space1), 10);
								kY = Integer.parseInt(kInput.substring(space1 + 1, space2), 10);
								area = Integer.parseInt(input.substring(space2 + 1, endBracket), 10);
							}
						} else {
							kInput = "";
						}
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			kX = (CAMERA_WIDTH / 2);
			kY = 300;
			area = 32000;
			verticalStrafe = 0.0;
			horizontalStrafe = 0.0;
		}
	
	}

	public double getkX() {
		return kX;
	}

	public double getkY() {
		return kY;
	}

	public double getArea() {
		return area;
	}
	
	public double getHorizontalStrafe() {
		return horizontalStrafe;
	}
	
	public double getVerticalStrafe() {
		return verticalStrafe;
	}

}