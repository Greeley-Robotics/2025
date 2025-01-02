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
import frc.robot.Robot;
import frc.robot.commands.autonomous.DriveForward;
import frc.robot.commands.autonomous.DriveInterval;


public class Drivetrain extends SubsystemBase {
    
    public final PWMSparkMax leftLeader = Robot.getLeftLeader();
    public final PWMSparkMax leftFollower = Robot.getLeftFollower();
    public final PWMSparkMax rightLeader = Robot.getRightLeader();
    public final PWMSparkMax rightFollower = Robot.getRightFollower();
    

    private final GenericEntry nt_SparkMaxSpeed1;
    private final GenericEntry nt_SparkMaxSpeed2;
    private final GenericEntry nt_SparkMaxSpeed3;
    private final GenericEntry nt_SparkMaxSpeed4;

    public Drivetrain(Joystick controller) {
        
        /** 
        leftLeader = Robot.getLeftLeader();
        leftFollower = Robot.getLeftFollower();
        rightLeader = Robot.getRightLeader();
        rightFollower = Robot.getRightFollower();
        */
        

        // Initialize Shuffleboard tab and entries
        ShuffleboardTab tab = Shuffleboard.getTab("Drivetrain");
        nt_SparkMaxSpeed1 = tab.add("Motor 1 Speed", 0).getEntry();
        nt_SparkMaxSpeed2 = tab.add("Motor 2 Speed", 0).getEntry();
        nt_SparkMaxSpeed3 = tab.add("Motor 3 Speed", 0).getEntry();
        nt_SparkMaxSpeed4 = tab.add("Motor 4 Speed", 0).getEntry();

    }

    PWMSparkMax m_motorRight = new PWMSparkMax(0);



    @Override
    public void periodic() {
        // Log motor speeds to shuffleboard
        nt_SparkMaxSpeed1.setDouble(Robot.getLeftLeader().get());
        nt_SparkMaxSpeed2.setDouble(Robot.getRightLeader().get());
        nt_SparkMaxSpeed1.setDouble(Robot.getLeftFollower().get());
        nt_SparkMaxSpeed2.setDouble(Robot.getRightFollower().get());
    }

    public Command driveForwardCommand() {
        return new DriveForward(this);
    }

    public Command driveIntervalCommand() {
        return new DriveInterval(this);
    }
}
