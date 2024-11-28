package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;


public class Intake extends SubsystemBase {
    private final PWMSparkMax intakeMotor;

    public Intake() {
        intakeMotor = new PWMSparkMax(IntakeConstants.kIntakeMotorPort);
    }

    public void runIntake() {
        intakeMotor.set(1.0);
    }

    public void reverseIntake() {
        intakeMotor.set(-1.0); 
    }

    public void stopIntake() {
        intakeMotor.set(0.0); 
    }
}
