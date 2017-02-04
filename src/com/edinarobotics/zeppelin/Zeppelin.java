package com.edinarobotics.zeppelin;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.zeppelin.commands.GamepadDriveCommand;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Zeppelin extends IterativeRobot {
	
	private Drivetrain drivetrain;
	
    public void robotInit() {
		Components.getInstance();
		Controls.getInstance();
		
		drivetrain = Components.getInstance().drivetrain;
    }
	
    public void disabledInit(){
    	stop();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {

    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	Gamepad gamepad0 = Controls.getInstance().gamepad0;
    	//drivetrain.setDefaultCommand(new GamepadDriveCommand(gamepad0));
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run(); 
//        System.out.println("Encoder front-left: " + drivetrain.getFrontLeft().getPosition());
//        System.out.println("Encoder front-right: " + drivetrain.getFrontRight().getPosition());
//        System.out.println("Encoder rear-left: " + drivetrain.getRearLeft().getPosition());
//        System.out.println("Encoder rear-right: " + drivetrain.getRearRight().getPosition());
    }
    
    public void testPeriodic() {

    }
    
    public void stop(){
    	drivetrain.setDrivetrain(0, 0, 0);
    }
    
}
