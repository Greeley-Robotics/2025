package frc.robot.subsystems;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.autonomous.DriveForward;
import frc.robot.commands.autonomous.DriveInterval;

public class Drivetrain extends SubsystemBase {
    public final PWMSparkMax sparkMax1;
    public final PWMSparkMax sparkMax2;
    public final PWMSparkMax sparkMax3;
    public final PWMSparkMax sparkMax4;

    private final GenericEntry nt_SparkMaxSpeed1;
    private final GenericEntry nt_SparkMaxSpeed2;
    private final GenericEntry nt_SparkMaxSpeed3;
    private final GenericEntry nt_SparkMaxSpeed4;

    public Drivetrain(Joystick controller) {
        sparkMax1 = new PWMSparkMax(DrivetrainConstants.kMotorControllerPort0);
        sparkMax2 = new PWMSparkMax(DrivetrainConstants.kMotorControllerPort1);
        sparkMax3 = new PWMSparkMax(DrivetrainConstants.kMotorControllerPort2);
        sparkMax4 = new PWMSparkMax(DrivetrainConstants.kMotorControllerPort3);

        // Initialize Shuffleboard tab and entries
        ShuffleboardTab tab = Shuffleboard.getTab("Drivetrain");
        nt_SparkMaxSpeed1 = tab.add("Motor 1 Speed", 0).getEntry();
        nt_SparkMaxSpeed2 = tab.add("Motor 2 Speed", 0).getEntry();
        nt_SparkMaxSpeed3 = tab.add("Motor 3 Speed", 0).getEntry();
        nt_SparkMaxSpeed4 = tab.add("Motor 4 Speed", 0).getEntry();
    }

    @Override
    public void periodic() {
        // Log motor speeds to shuffleboard
        nt_SparkMaxSpeed1.setDouble(sparkMax1.get());
        nt_SparkMaxSpeed2.setDouble(sparkMax2.get());
        nt_SparkMaxSpeed3.setDouble(sparkMax3.get());
        nt_SparkMaxSpeed4.setDouble(sparkMax4.get());
    }

    public Command driveForwardCommand() {
        return new DriveForward(this);
    }

    public Command driveIntervalCommand() {
        return new DriveInterval(this);
    }
}
