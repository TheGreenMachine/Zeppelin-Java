package com.edinarobotics.zeppelin;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.utils.pid.PIDTuningManager;
import com.edinarobotics.zeppelin.commands.GamepadDriveCommand;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Zeppelin extends IterativeRobot {

	private Drivetrain drivetrain;

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
		//drivetrain.setDefaultCommand(new GamepadDriveCommand(gamepad0));
	}
 
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
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
