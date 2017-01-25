package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateXDegreesCommand extends Command implements PIDOutput {
	
	private Drivetrain drivetrain;
	private AHRS gyro;
	private double initialPosition;
	private float degrees;
	
	private PIDController turnController;
	private double rotationSpeed;
	
	private static final double kP = 0.03;
	private static final double kI = 0.00;
	private static final double kD = 0.00;
	private static final double kF = 0.00;

    public RotateXDegreesCommand(float degrees) {
    	super("rotatexdegreescommand");
    	gyro = Components.getInstance().navX;
    	
    	turnController = new PIDController(kP, kI, kD, kF, gyro, this);
    	turnController.setInputRange(-180.0f, 180.0f);
    	turnController.setOutputRange(-1.0, 1.0);
    	turnController.setAbsoluteTolerance(2.0f);
    	turnController.setContinuous(true);
    	
    	this.degrees = degrees;
    	drivetrain = Components.getInstance().drivetrain;
        requires(drivetrain);
    }

    protected void initialize() {
    	initialPosition = gyro.getAngle();
    	turnController.setSetpoint(degrees);
    }

    protected void execute() {
    	turnController.enable();
    	drivetrain.setDrivetrain(0.0, 0.0, rotationSpeed);
    }

    protected boolean isFinished() {
        return turnController.onTarget();
    }

    protected void end() {
    	drivetrain.setDrivetrain(0.0, 0.0, 0.0);
    }

    protected void interrupted() {
    	end();
    }

	@Override
	public void pidWrite(double output) {
		rotationSpeed = output;
	}
}
