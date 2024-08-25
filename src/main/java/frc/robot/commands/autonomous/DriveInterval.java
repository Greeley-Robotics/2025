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
            m_drivetrain.sparkMax1.set(valToSet);
            m_drivetrain.sparkMax2.set(valToSet);
            m_drivetrain.sparkMax3.set(-valToSet);
            m_drivetrain.sparkMax4.set(-valToSet);
        } else {
            m_drivetrain.sparkMax1.set(0);
            m_drivetrain.sparkMax2.set(0);
            m_drivetrain.sparkMax3.set(0);
            m_drivetrain.sparkMax4.set(0);
        }
    }

    @Override 
    public void end(boolean interrupted) {
        m_drivetrain.sparkMax1.stopMotor();
        m_drivetrain.sparkMax2.stopMotor();
        m_drivetrain.sparkMax3.stopMotor();
        m_drivetrain.sparkMax4.stopMotor();
        m_timer.stop();
    }   

    @Override
    public boolean isFinished() {
        return false;
    }
}
