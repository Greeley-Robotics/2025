package frc.robot.commands.teleoperated;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDrive extends Command {
    private final Drivetrain m_drivetrain;
    private final Supplier<Double> speedFunction, turnFunction;

    public ArcadeDrive(Drivetrain subsystem,
                       Supplier<Double> speedFunction,
                       Supplier<Double> turnFunction) {
        this.speedFunction = speedFunction;
        this.turnFunction = turnFunction;
        this.m_drivetrain = subsystem;
        addRequirements(m_drivetrain);
    }

    @Override
    public void initialize() {
        System.out.println("ARCADE DRIVE HAS STARTED!!!");
    }

    @Override
    public void execute() {
        double realTimeSpeed = speedFunction.get();
        double realTimeTurn = turnFunction.get();

        double left = realTimeSpeed + realTimeTurn;
        double right = realTimeSpeed - realTimeTurn;

        m_drivetrain.sparkMax1.set(left);
        m_drivetrain.sparkMax2.set(left);
        m_drivetrain.sparkMax3.set(-right);
        m_drivetrain.sparkMax4.set(-right);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("ArcadeDrive ended!");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
