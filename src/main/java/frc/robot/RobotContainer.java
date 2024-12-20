// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.teleoperated.ArcadeDrive;
import frc.robot.commands.teleoperated.IntakeCommand;
import frc.robot.commands.teleoperated.ReverseIntakeCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  

  // Joystick or Controller
  private final Joystick m_driverController =
      new Joystick(OperatorConstants.kDriverControllerPort);

  private final Drivetrain m_drivetrain = new Drivetrain(m_driverController);
  private final Intake m_intake = new Intake();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    System.out.println("DEFAULT ARCADE DRIVE COMMAND! (TELEOPERATED)!");
    m_drivetrain.setDefaultCommand(new ArcadeDrive(m_drivetrain,
      () -> -m_driverController.getRawAxis(OperatorConstants.kArcadeDriveSpeedAxis),
      () -> m_driverController.getRawAxis(OperatorConstants.kArcadeDriveTurnAxis)
    ));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    new JoystickButton(m_driverController, 1)
                      .whileTrue(new IntakeCommand(m_intake));
    new JoystickButton(m_driverController, 2)
                      .whileTrue(new ReverseIntakeCommand(m_intake));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getDriveForwardAuto() {
    // An example command will be run in autonomous
    return Autos.driveForwardAuto(m_drivetrain);
  }

  public Command getDriveIntervalAuto() {
    return Autos.driveIntervalAuto(m_drivetrain);
  }
  
  public Command getIntakeAuto() {
    return Autos.intakeAuto(m_intake);
  }
}
