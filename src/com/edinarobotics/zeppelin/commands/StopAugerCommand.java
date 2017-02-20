package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Augers;

import edu.wpi.first.wpilibj.command.Command;

public class StopAugerCommand extends Command {

	private Augers augers;
	
    public StopAugerCommand() {
    	super("stopaugercommand");
    	augers = Components.getInstance().augers;
    	requires(augers);
    }

    protected void initialize() {
    	augers.setAutoRunning(false);
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	
    }
}
