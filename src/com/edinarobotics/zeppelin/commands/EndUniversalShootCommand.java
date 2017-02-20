package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class EndUniversalShootCommand extends Command{

	private Shooter shooter;
	
	public EndUniversalShootCommand(){
		super("enduniversalshootcommand");
		this.shooter = Components.getInstance().shooter;
		requires(shooter);
	}

	protected void initialize(){
		shooter.setAutoRunning(false);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
