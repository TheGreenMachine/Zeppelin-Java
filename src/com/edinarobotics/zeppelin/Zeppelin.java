package com.edinarobotics.zeppelin;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.utils.pid.PIDTuningManager;
import com.edinarobotics.zeppelin.commands.AutonomousCommand;
import com.edinarobotics.zeppelin.commands.AutonomousCommand.AutoMode;
import com.edinarobotics.zeppelin.commands.GamepadDriveCommand;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Zeppelin extends IterativeRobot {

	private SendableChooser<AutoMode> autoChooser;
	
	private Drivetrain drivetrain;
	private SerialPort serialPort;

	public void robotInit() {
		Components.getInstance();
		Controls.getInstance();

		drivetrain = Components.getInstance().drivetrain;
		
		setupDashboard();
	}

	public void autonomousInit() {
		if (autoChooser == null) {
			setupDashboard();
		}
		
		Command cmd = new AutonomousCommand((AutoMode) autoChooser.getSelected());
		cmd.start();
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
	
	private void setupDashboard() {
		autoChooser = new SendableChooser<>();
		
		autoChooser.addObject("Center Gear", AutoMode.CENTER_GEAR);
		autoChooser.addObject("Left Gear", AutoMode.LEFT_GEAR);
		autoChooser.addObject("Right Gear", AutoMode.RIGHT_GEAR);
		autoChooser.addObject("Baseline", AutoMode.BASELINE);
		autoChooser.addObject("Nothing", AutoMode.NOTHING);

		SmartDashboard.putData("Auto Chooser", autoChooser);
	}

}
