package com.edinarobotics.zeppelin;

import java.util.ArrayList;
import java.util.List;

import com.edinarobotics.utils.gamepad.FilteredGamepad;
import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.utils.gamepad.gamepadfilters.DeadzoneFilter;
import com.edinarobotics.utils.gamepad.gamepadfilters.GamepadFilter;
import com.edinarobotics.utils.gamepad.gamepadfilters.GamepadFilterSet;
import com.edinarobotics.utils.gamepad.gamepadfilters.PowerFilter;
import com.edinarobotics.zeppelin.commands.DriveXInchesCommand;
import com.edinarobotics.zeppelin.commands.RotateXDegreesCommand;
import com.edinarobotics.zeppelin.commands.SetCollectorStateCommand;
import com.edinarobotics.zeppelin.commands.ToggleAnchorCommand;
import com.edinarobotics.zeppelin.commands.ToggleDropWheelCommand;
import com.edinarobotics.zeppelin.subsystems.Collector.CollectorState;

public class Controls {

	private static Controls instance;

	public Gamepad gamepad0;

	private Controls() {

		List<GamepadFilter> gamepadFilters = new ArrayList<GamepadFilter>();
		gamepadFilters.add(new DeadzoneFilter(0.1));
		gamepadFilters.add(new PowerFilter(1));
		GamepadFilterSet driveGamepadFilterSet = new GamepadFilterSet(gamepadFilters);
		gamepad0 = new FilteredGamepad(0, driveGamepadFilterSet);

		gamepad0.rightTrigger().whenPressed(new SetCollectorStateCommand(CollectorState.HIGH));
		gamepad0.rightTrigger().whenReleased(new SetCollectorStateCommand(CollectorState.OFF));

		gamepad0.rightBumper().whenPressed(new SetCollectorStateCommand(CollectorState.LOW));
		gamepad0.rightBumper().whenReleased(new SetCollectorStateCommand(CollectorState.OFF));

		gamepad0.diamondDown().whenPressed(new ToggleDropWheelCommand());
		gamepad0.diamondDown().whenReleased(new ToggleDropWheelCommand());

		gamepad0.diamondUp().whenPressed(new ToggleAnchorCommand());
		gamepad0.diamondUp().whenReleased(new ToggleAnchorCommand());
		
		gamepad0.diamondLeft().whenPressed(new DriveXInchesCommand(-96));
		gamepad0.diamondRight().whenPressed(new DriveXInchesCommand(96));
		
		gamepad0.dPadLeft().whenPressed(new RotateXDegreesCommand(-90f));
		gamepad0.dPadDown().whenPressed(new RotateXDegreesCommand(0f));
		gamepad0.dPadRight().whenPressed(new RotateXDegreesCommand(90f));
		
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
