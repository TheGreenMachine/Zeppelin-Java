package com.edinarobotics.zeppelin;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.utils.pid.PIDTuningManager;
import com.edinarobotics.zeppelin.commands.GamepadDriveCommand;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Zeppelin extends IterativeRobot {

	private Drivetrain drivetrain;
	private SerialPort serialPort;

	public void robotInit() {
		Components.getInstance();
		Controls.getInstance();

		drivetrain = Components.getInstance().drivetrain;
	}

	public void autonomousInit() {

	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		Gamepad gamepad0 = Controls.getInstance().gamepad0;
		drivetrain.setDefaultCommand(new GamepadDriveCommand(gamepad0));
		this.serialPort = new SerialPort(9600, SerialPort.Port.kMXP, 8,					//examine settings in RoboRealm:Serial to get the proper inputs. inputs in order are: baud rate, port type, data bits, parity, stop bits
				SerialPort.Parity.kNone, SerialPort.StopBits.kOne);
	}
 
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		try {
			String temp = serialPort.readString();		

			if (temp.length() > 0) {
				System.out.println("Read String: " + temp);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
 
	public void testInit() {
		LiveWindow.setEnabled(false);
		teleopInit();
	}

	public void testPeriodic() {
		teleopPeriodic();
		PIDTuningManager.getInstance().runTuning();
	}

	public void disabledInit() {
		stop();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void stop() {
		drivetrain.setDrivetrain(0, 0, 0);
	}

}
