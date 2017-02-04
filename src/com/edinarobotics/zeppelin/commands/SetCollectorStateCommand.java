package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Collector;
import com.edinarobotics.zeppelin.subsystems.Collector.CollectorState;

import edu.wpi.first.wpilibj.command.Command;

public class SetCollectorStateCommand extends Command{
	
	private Collector collector;
	private CollectorState state;
	
	public SetCollectorStateCommand(CollectorState state){
		super("setcollectorstatecommand");
		this.state = state;
		collector = Components.getInstance().collector;
		requires(collector);
	}
	
	@Override
	protected void initialize() {
		collector.setCollectorState(state);
	}

	@Override
	protected void execute() {

	}


	@Override
	protected boolean isFinished() {
		return true;
	}
	
	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {

	}


}
