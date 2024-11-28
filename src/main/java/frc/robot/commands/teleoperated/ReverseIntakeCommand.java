package frc.robot.commands.teleoperated;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class ReverseIntakeCommand extends Command {
    private final Intake m_intake;

    public ReverseIntakeCommand(Intake intakeSubsystem) {
        this.m_intake = intakeSubsystem;
        addRequirements(m_intake);
    }

    @Override
    public void initialize() {
        m_intake.stopIntake();
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
        m_intake.stopIntake();
    }

    @Override
    public boolean isFinished() {
        return false; 
    }
}
