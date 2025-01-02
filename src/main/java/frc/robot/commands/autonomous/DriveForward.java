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
        m_drivetrain.leftLeader.set(0.0);
       // m_drivetrain.leftFollower.set(0.0);
        m_drivetrain.rightLeader.set(0.0);
        //m_drivetrain.rightFollower.set(0.0);
    }

    @Override
    public void execute() {
        // Changed all values to positive because they are set to negative because of setInverted in Robot class
        m_drivetrain.leftLeader.set(valToSet);
       // m_drivetrain.leftFollower.set(valToSet);
        m_drivetrain.rightLeader.set(valToSet);
        //m_drivetrain.rightFollower.set(valToSet);
    }

    @Override 
    public void end(boolean interrupted) {
        m_drivetrain.leftLeader.stopMotor();
      //  m_drivetrain.leftFollower.stopMotor();
        m_drivetrain.rightLeader.stopMotor();
       // m_drivetrain.rightFollower.stopMotor();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
