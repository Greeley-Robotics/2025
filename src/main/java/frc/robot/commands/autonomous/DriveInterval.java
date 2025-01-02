package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.AutonomousConstants;
import frc.robot.subsystems.Drivetrain;

public class DriveInterval extends Command {
    private final Drivetrain m_drivetrain;
    private double valToSet = AutonomousConstants.kAutoDriveIntervalSpeed;
    private final Timer m_timer;
    private boolean m_motorOn;
    private static final double INTERVAL = 3.0;

    public DriveInterval(Drivetrain subsystem) {
        m_drivetrain = subsystem;
        m_timer = new Timer();
        m_motorOn = false;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        m_timer.reset();
        m_timer.start();
    }

    @Override
    public void execute() {
        if (m_timer.get() >= INTERVAL) {
            m_timer.reset();
            m_motorOn = !m_motorOn;
        }

        if (m_motorOn) {
            // Changed all values to positive because they are set to negative because of setInverted in Robot class
            m_drivetrain.leftLeader.set(valToSet);
            //m_drivetrain.leftFollower.set(valToSet);
            m_drivetrain.rightLeader.set(valToSet);
            //m_drivetrain.rightFollower.set(valToSet);
        } else {
            m_drivetrain.leftLeader.set(0);
            //m_drivetrain.leftFollower.set(0);
            m_drivetrain.rightLeader.set(0);
            //m_drivetrain.rightFollower.set(0);
        }
    }

    @Override 
    public void end(boolean interrupted) {
        m_drivetrain.leftLeader.stopMotor();
        //m_drivetrain.leftFollower.stopMotor();
        m_drivetrain.rightLeader.stopMotor();
        //m_drivetrain.rightFollower.stopMotor();
        m_timer.stop();
    }   

    @Override
    public boolean isFinished() {
        return false;
    }
}
