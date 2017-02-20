package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.utils.log.Logging;
import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;
import com.edinarobotics.zeppelin.subsystems.Vision;

import edu.wpi.first.wpilibj.command.Command;

public class RunVisionStrafeCommand extends Command {

	private Vision vision;
	private Drivetrain drivetrain;
	
	private Logging logging;

	public RunVisionStrafeCommand() {
		super("runvisionstrafecommand");
		vision = Components.getInstance().vision;
		drivetrain = Components.getInstance().drivetrain;
		requires(vision);
		requires(drivetrain);
	}

	@Override
	protected void initialize() {
		logging = new Logging("RunVisionStrafeCommand - " + System.currentTimeMillis());
		logging.log("X, Y, Area, HorizontalStrafe");
		
		drivetrain.setDropWheel(false);
	}

	@Override
	protected void execute() {
		vision.runHorizontalStrafe();
		logging.log(vision.getkX() + ", " + vision.getkY() + ", " + vision.getArea() + ", " + vision.getHorizontalStrafe());
	}

	@Override
	protected boolean isFinished() {
		return vision.isXAtTarget();
	}

	@Override
	protected void end() {
		drivetrain.setDrivetrain(0.0, 0.0, 0.0);
		drivetrain.setDropWheel(true);
		logging.close();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
