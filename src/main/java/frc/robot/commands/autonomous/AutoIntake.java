package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.AutonomousConstants;
import frc.robot.subsystems.Intake;

public class AutoIntake extends Command {
    private final Intake m_intake;
    private double duration = AutonomousConstants.kAutoIntakeDuration;
    private long startTime;
    
    public AutoIntake(Intake subsystem) {
        m_intake = subsystem;
        addRequirements(m_intake);
    }

    @Override
    public void initialize() {
        m_intake.runIntake();
        startTime = System.currentTimeMillis();
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
        m_intake.stopIntake();
    }

    @Override
    public boolean isFinished() {
        return System.currentTimeMillis() - startTime >= duration;
    }
}
