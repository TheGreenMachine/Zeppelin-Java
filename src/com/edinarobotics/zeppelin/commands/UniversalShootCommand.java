package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Augers;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;
import com.edinarobotics.zeppelin.subsystems.Shooter;
import com.edinarobotics.zeppelin.subsystems.Shooter.ShooterSpeed;

import edu.wpi.first.wpilibj.command.Command;

public class UniversalShootCommand extends Command{

	private Shooter shooter;
	private Augers augers;
	private Drivetrain drivetrain;
	private boolean notShootingYet;
	
	private long startTime;
	
	public UniversalShootCommand(){
		super("universalshootcommand");
		this.shooter = Components.getInstance().shooter;
		this.augers = Components.getInstance().augers;
		this.drivetrain = Components.getInstance().drivetrain;
		requires(shooter);
		requires(augers);
		requires(drivetrain);
	}
	
	protected void initialize(){
		drivetrain.setAnchors(true);
		shooter.setAutoRunning(true);
		shooter.setSpeed(ShooterSpeed.ON);
		notShootingYet = true;
		startTime = System.nanoTime();
	}
	
	protected void execute(){
		if (System.nanoTime()>startTime + 1000000000 && notShootingYet){
			augers.setSpeed(0.6);
			notShootingYet = false;
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return !shooter.isAutoRunning();
	}

	protected void end(){
		shooter.setSpeed(ShooterSpeed.OFF);
		augers.setSpeed(0.0);
		drivetrain.setAnchors(false);
	}
	
}
