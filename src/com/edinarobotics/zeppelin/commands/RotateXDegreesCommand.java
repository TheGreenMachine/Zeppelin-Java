package com.edinarobotics.zeppelin.commands;

import com.edinarobotics.utils.log.Logging;
import com.edinarobotics.utils.pid.PIDConfig;
import com.edinarobotics.utils.pid.PIDTuningManager;
import com.edinarobotics.zeppelin.Components;
import com.edinarobotics.zeppelin.subsystems.Drivetrain;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

public class RotateXDegreesCommand extends Command implements PIDOutput {

	private Drivetrain drivetrain;
	private AHRS gyro;
	private float degrees, initialDegrees;

	private PIDController turnController;
	private PIDConfig PIDConfig;
	private double rotationSpeed;

	private static final double kP = 0.025;
	private static final double kI = 0.0000;
	private static final double kD = 0.0000;
	private static final double kF = 0.000;
	
	private Logging logging;

	public RotateXDegreesCommand(float degrees) {
		super("rotatexdegreescommand");
		gyro = Components.getInstance().navX;
		gyro.reset();

		turnController = new PIDController(kP, kI, kD, kF, gyro, this);
		turnController.setOutputRange(-0.5, 0.5);
		turnController.setAbsoluteTolerance(2.0f);
		turnController.setContinuous(true);

		PIDConfig = PIDTuningManager.getInstance().getPIDConfig("RotateXDegrees");

		this.degrees = degrees;
		drivetrain = Components.getInstance().drivetrain;
		requires(drivetrain);
	}

	protected void initialize() {
		logging = new Logging("RotateXDegreesCommand - " + System.currentTimeMillis());
		logging.log("currentGyro, targetGyro");
		turnController.setSetpoint(degrees);
	}

	protected void execute() {
		turnController.enable();

		turnController.setPID(PIDConfig.getP(kP), PIDConfig.getI(kI), 
				PIDConfig.getD(kD), PIDConfig.getF(kF));

		PIDConfig.setSetpoint(degrees);
		PIDConfig.setValue(rotationSpeed);

		drivetrain.setDrivetrain(0.0, 0.0, rotationSpeed);
		System.out.println("Target:" + (degrees + initialDegrees));
		System.out.println("Current: " + gyro.getAngle());
		
		logging.log(gyro.getAngle()+", "+(gyro.getAngle() + degrees));
	}

	protected boolean isFinished() {
		return turnController.onTarget() && gyro.getRate() <= 2;
	}

	protected void end() {
		drivetrain.setDrivetrain(0.0, 0.0, 0.0);
		logging.close();
	}

	protected void interrupted() {
		end();
	}

	@Override
	public void pidWrite(double output) {
		rotationSpeed = output;
	}
	
}
