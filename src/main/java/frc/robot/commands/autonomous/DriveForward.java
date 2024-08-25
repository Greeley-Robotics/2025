package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.AutonomousConstants;
import frc.robot.subsystems.Drivetrain;

public class DriveForward extends Command {
    private final Drivetrain m_drivetrain;
    private double valToSet = AutonomousConstants.kAutoDriveForwardSpeed;

    public DriveForward(Drivetrain subsystem) {
        m_drivetrain = subsystem;
        addRequirements(m_drivetrain);
    }

    @Override
    public void initialize() {
        m_drivetrain.sparkMax1.set(0.0);
        m_drivetrain.sparkMax2.set(0.0);
        m_drivetrain.sparkMax3.set(0.0);
        m_drivetrain.sparkMax4.set(0.0);
    }

    @Override
    public void execute() {
        m_drivetrain.sparkMax1.set(valToSet);
        m_drivetrain.sparkMax2.set(valToSet);
        m_drivetrain.sparkMax3.set(-valToSet);
        m_drivetrain.sparkMax4.set(-valToSet);
    }

    @Override 
    public void end(boolean interrupted) {
        m_drivetrain.sparkMax1.stopMotor();
        m_drivetrain.sparkMax2.stopMotor();
        m_drivetrain.sparkMax3.stopMotor();
        m_drivetrain.sparkMax4.stopMotor();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
