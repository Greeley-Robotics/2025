package frc.robot.subsystems;

import java.security.GeneralSecurityException;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.commands.teleoperated.IntakeCommand;
import frc.robot.commands.autonomous.AutoIntake;


public class Intake extends SubsystemBase {
    private final PWMSparkMax intakeMotor;

    private final GenericEntry nt_IntakeMotorSpeed;

    public Intake() {
        intakeMotor = new PWMSparkMax(IntakeConstants.kIntakeMotorPort);

        ShuffleboardTab tab = Shuffleboard.getTab("Intake");
        nt_IntakeMotorSpeed = tab.add("Intake Motor Speed", 0).getEntry();
    }

    @Override
    public void periodic() {
        // Log motor speeds to shuffleboard
        nt_IntakeMotorSpeed.setDouble(intakeMotor.get());
    }


    public void runIntake() {
        intakeMotor.set(IntakeConstants.kIntakeMotorSpeed);
    }

    public void reverseIntake() {
        intakeMotor.set(-IntakeConstants.kIntakeMotorSpeed); 
    }

    public void stopIntake() {
        intakeMotor.set(0.0); 
    }

    public Command autoIntakeCommand() {
        return new AutoIntake(this);
    }
}

