package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveXInchesHorizontalCommand extends Command {
	
	private Drivetrain drivetrain;
	private int inches;
	
	private int ticks;
	
	private final int CONVERSION_FACTOR = 39;
	private final int THRESHOLD = 5;
	
	private final double FAST_SPEED = .7;
	private final double SLOW_SPEED = .3;
	
	public DriveXInchesHorizontalCommand(int inches) {
		super("drivexincheshorizontalcommand");
		this.inches = inches;
		drivetrain = Components.getInstance().drivetrain;
		ticks = (inches * CONVERSION_FACTOR) + drivetrain.getMiddle().getEncPosition();
		requires(drivetrain);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		int currentMiddlePosition = drivetrain.getMiddle().getEncPosition();
		
		if(ticks > currentMiddlePosition) {
			if(ticks - currentMiddlePosition < 1000) {
				drivetrain.setDrivetrain(0.0, SLOW_SPEED, 0.0);
			} else {
				drivetrain.setDrivetrain(0.0, FAST_SPEED, 0.0);
			}
		} else {
			if (currentMiddlePosition - ticks < 1000) {
				drivetrain.setDrivetrain(0.0, SLOW_SPEED, 0.0);
			} else {
				drivetrain.setDrivetrain(0.0, FAST_SPEED, 0.0);
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(ticks - drivetrain.getMiddle().getEncPosition()) 
				< THRESHOLD;
	}

	@Override
	protected void end() {
		drivetrain.setDrivetrain(0.0, 0.0, 0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
