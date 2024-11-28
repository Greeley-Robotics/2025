package frc.robot.commands.teleoperated;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IntakeCommand extends Command {
    private final Intake m_intake;

    public IntakeCommand(Intake intakeSubsystem) {
        this.m_intake = intakeSubsystem;
        addRequirements(m_intake);
    }

    @Override
    public void initialize() {
        m_intake.runIntake();
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
