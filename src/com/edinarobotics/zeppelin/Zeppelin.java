package com.edinarobotics.zeppelin;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.utils.pid.PIDTuningManager;
import com.edinarobotics.zeppelin.commands.AutonomousCommand;
import com.edinarobotics.zeppelin.commands.AutonomousCommand.AutoMode;
import com.edinarobotics.zeppelin.commands.GamepadAugerCommand;
import com.edinarobotics.zeppelin.commands.GamepadClimberCommand;
import com.edinarobotics.zeppelin.commands.GamepadDriveCommand;
import com.edinarobotics.zeppelin.subsystems.Augers;
import com.edinarobotics.zeppelin.subsystems.Climber;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Zeppelin extends IterativeRobot {

	private SendableChooser<AutoMode> autoChooser;
	private Command autoCommand = null;

	private Drivetrain drivetrain;
	private Augers augers;
	private Climber climber;
	
	public void robotInit() {
		Components.getInstance();
		Controls.getInstance();

		drivetrain = Components.getInstance().drivetrain;
		augers = Components.getInstance().augers;
		climber = Components.getInstance().climber;
		
		setupDashboard();
	}

	public void autonomousInit() {
		if (autoChooser == null) {
			setupDashboard();
		}

		autoCommand = new AutonomousCommand((AutoMode) autoChooser.getSelected());
		autoCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		if (autoCommand != null) {
			autoCommand.cancel();
		}

		Gamepad gamepad0 = Controls.getInstance().gamepad0;
		Gamepad gamepad1 = Controls.getInstance().gamepad1;
		
		drivetrain.setDefaultCommand(new GamepadDriveCommand(gamepad0));
		augers.setDefaultCommand(new GamepadAugerCommand(gamepad1));
		climber.setDefaultCommand(new GamepadClimberCommand(gamepad1));
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

	private void setupDashboard() {
		autoChooser = new SendableChooser<>();

		autoChooser.addObject("Center Gear", AutoMode.CENTER_GEAR);
		autoChooser.addObject("Left Gear", AutoMode.LEFT_GEAR);
		autoChooser.addObject("Right Gear", AutoMode.RIGHT_GEAR);
		autoChooser.addObject("Baseline", AutoMode.BASELINE);
		autoChooser.addDefault("Nothing", AutoMode.NOTHING);

		SmartDashboard.putData("Auto Chooser", autoChooser);
	}

}
