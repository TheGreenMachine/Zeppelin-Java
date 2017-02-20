package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Augers;

import edu.wpi.first.wpilibj.command.Command;

public class SetAugerSpeedCommand extends Command {

	private Augers augers;
	private double speed;
	
	public SetAugerSpeedCommand(double speed) {
		super("setaugerspeedcommand");
		augers = Components.getInstance().augers;
		this.speed = speed;
		requires(augers);
	}
	
    protected void initialize() {
    	augers.setSpeed(speed);
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
    	return !augers.isAutoRunning();
    }

    protected void end() {
    	augers.setSpeed(0.0);
    }

    protected void interrupted() {
    	end();
    }
}
