<p align="center">
  <img src="http://edinarobotics.com/sites/all/themes/greenmachine/assets/images/Logo.gif">
</p>

# Zeppelin-Java
This is the Java code repository for FRC Team 1816's 2017 robot, Zeppelin.

This code is partitioned into several packages, each responsible for an individual characteristic of the robot's overall functionality. This document attempts to explain each responsibility of each package, and the setup instructions for the project as a whole. Additional information can be found in the project's wiki. 

## Package Functionality
- com.edinarobotics.zeppelin

  Includes the robot's main tasks and includes files with all of the instances and numerical constants for the subsytems that are used throughout the code. For example, Controls holds all of the instances of the Gamepads, and Components holds instances of every subsystem that is featured throughout our code.
  
  
- com.edinarobotics.zeppelin.commands

  Commands are a series of actions that occur periodically on the robot, and conventionally implement methods involved within the subsystems that they require. 


- com.edinarobotics.zeppelin.subsystems

  Subsystems are parts of the robot that are independent controllers for a variety of objects located throughout on robot. Each subsystem is an instance of our in-house application program interface (API), called Subsystem1816. There can only be a single instance of each subsystem throughout your code (a robot cannot have more than one Drivetrain or Shooter in the code).

## Configuring
This project requires the following source files or 3rd party libraries to be deployed:
- 1816-Utils-Java
  - 1816-Utils-Java is our in-house API designed to allow common code practices and decrease the amount of code that must be rewritten every season. This project's source code is available at: https://github.com/TheGreenMachine/1816-Utils-Java.

- Cross the Road Electronics
  - Cross the Road Electronics is a new addition to the required libraries that must be implemented as the CANTalon class was discarded in the 2017 Season. This project's download link is located at: http://www.ctr-electronics.com/talon-srx.html#product_tabs_technical_resources.
  
- navX MXP
  - navX-MXP is a 9-axis inertial/magnetic sensor and motion processor. The navX-MXP Java software library supports access to navX-MXP via all four of the navX-MXP communication interfaces. This project's download link is located at: http://www.pdocs.kauailabs.com/navx-mxp/software/roborio-libraries/java/.

Follow the instructions in the file SAMPLElib1816.properties to configure the build.
