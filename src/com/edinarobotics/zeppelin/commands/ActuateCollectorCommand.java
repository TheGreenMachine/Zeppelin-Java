package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Collector;

import edu.wpi.first.wpilibj.command.Command;

public class ActuateCollectorCommand extends Command {

	private Collector collector;
	private boolean toggle;
	
    public ActuateCollectorCommand(boolean toggle) {
    	super("actuatecollectorcommand");
    	this.collector = Components.getInstance().collector;
    	this.toggle = toggle;
    	requires(collector);
    }

    protected void initialize() {
    	collector.actuateCollector(toggle);
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
