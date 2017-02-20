package com.edinarobotics.zeppelin;

import java.util.ArrayList;
import java.util.List;

import com.edinarobotics.utils.gamepad.FilteredGamepad;
import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.utils.gamepad.gamepadfilters.DeadzoneFilter;
import com.edinarobotics.utils.gamepad.gamepadfilters.GamepadFilter;
import com.edinarobotics.utils.gamepad.gamepadfilters.GamepadFilterSet;
import com.edinarobotics.utils.gamepad.gamepadfilters.PowerFilter;
import com.edinarobotics.zeppelin.commands.ActuateCollectorCommand;
import com.edinarobotics.zeppelin.commands.EndUniversalShootCommand;
import com.edinarobotics.zeppelin.commands.SetAugerSpeedCommand;
import com.edinarobotics.zeppelin.commands.SetCollectorStateCommand;
import com.edinarobotics.zeppelin.commands.SetShooterStateCommand;
import com.edinarobotics.zeppelin.commands.StopAugerCommand;
import com.edinarobotics.zeppelin.commands.ToggleAnchorCommand;
import com.edinarobotics.zeppelin.commands.ToggleDropWheelCommand;
import com.edinarobotics.zeppelin.commands.ToggleSlowModeCommand;
import com.edinarobotics.zeppelin.commands.UniversalShootCommand;
import com.edinarobotics.zeppelin.subsystems.Collector.CollectorState;
import com.edinarobotics.zeppelin.subsystems.Shooter.ShooterSpeed;

public class Controls {

	private static Controls instance;

	public Gamepad gamepad0;
	public Gamepad gamepad1;

	private Controls() {

		List<GamepadFilter> gamepadFilters = new ArrayList<GamepadFilter>();
		gamepadFilters.add(new DeadzoneFilter(0.1));
		gamepadFilters.add(new PowerFilter(1));
		GamepadFilterSet driveGamepadFilterSet = new GamepadFilterSet(gamepadFilters);
		gamepad0 = new FilteredGamepad(0, driveGamepadFilterSet);
		gamepad1 = new FilteredGamepad(1, driveGamepadFilterSet);
		
		//Jacob's Controls
		gamepad0.rightTrigger().whenPressed(new ToggleSlowModeCommand(true));
		gamepad0.rightTrigger().whenReleased(new ToggleSlowModeCommand(false));
		
		gamepad0.leftTrigger().whenPressed(new SetCollectorStateCommand(CollectorState.FORWARDS));
		gamepad0.leftTrigger().whenReleased(new SetCollectorStateCommand(CollectorState.OFF));
		
		gamepad0.leftBumper().whenPressed(new ToggleDropWheelCommand());
		gamepad0.leftBumper().whenReleased(new ToggleDropWheelCommand());
		
		gamepad0.rightBumper().whenPressed(new ToggleAnchorCommand());
		gamepad0.rightBumper().whenReleased(new ToggleAnchorCommand());
		
		//Aiden's Controls
		gamepad1.dPadUp().whenPressed(new SetCollectorStateCommand(CollectorState.FORWARDS));
		gamepad1.dPadUp().whenReleased(new SetCollectorStateCommand(CollectorState.OFF));
		
		gamepad1.dPadDown().whenPressed(new SetCollectorStateCommand(CollectorState.REVERSE));
		gamepad1.dPadDown().whenReleased(new SetCollectorStateCommand(CollectorState.OFF));
		
		gamepad1.dPadRight().whenPressed(new ActuateCollectorCommand(true));
		gamepad1.dPadLeft().whenPressed(new ActuateCollectorCommand(false));
		
		gamepad1.rightBumper().whenPressed(new SetShooterStateCommand(ShooterSpeed.ON));
		gamepad1.rightBumper().whenReleased(new SetShooterStateCommand(ShooterSpeed.OFF));
		
		gamepad1.leftBumper().whenPressed(new SetAugerSpeedCommand(0.6));
		gamepad1.leftBumper().whenReleased(new StopAugerCommand());
		
		gamepad1.diamondDown().whenPressed(new UniversalShootCommand());
		gamepad1.diamondDown().whenReleased(new EndUniversalShootCommand());
		
	}

	/**
	 * Returns the proper instance of Controls. This method creates a new
	 * Controls object the first time it is called and returns that object for
	 * each subsequent call.
	 *
	 * @return The current instance of Controls.
	 */
	public static Controls getInstance() {
		if (instance == null) {
			instance = new Controls();
		}

		return instance;
	}

}
