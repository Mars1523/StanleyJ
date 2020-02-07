package frc.team1523.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.team1523.robot.subsystems.DriveSubsystem;

public class RobotContainer {
    private static final String kDefaultAuto = "Default";
    private static final String kCustomAuto = "My Auto";
    private final SendableChooser<String> chooser = new SendableChooser<>();

    private final XboxController primaryGamepad = new XboxController(0);

    private final DriveSubsystem driveSubsystem = new DriveSubsystem();


    public RobotContainer() {
        configureButtonBindings();

        chooser.setDefaultOption("Default Auto", kDefaultAuto);
        chooser.addOption("My Auto", kCustomAuto);
        Shuffleboard.getTab("Drive").add("Auto choicess", chooser);

        driveSubsystem.setDefaultCommand(
                new RunCommand(() ->
                        driveSubsystem.arcadeDrive(
                                primaryGamepad.getTriggerAxis(GenericHID.Hand.kLeft) +
                                        -primaryGamepad.getTriggerAxis(GenericHID.Hand.kLeft),
                                -primaryGamepad.getX(GenericHID.Hand.kLeft) * .75),
                        driveSubsystem));
    }

    private void configureButtonBindings() {
    }

    public Command getAutonomousCommand() {
        String m_autoSelected = chooser.getSelected();
        switch (m_autoSelected) {
            case kCustomAuto:
                return new InstantCommand();
            case kDefaultAuto:
            default:
                return new InstantCommand();
        }
    }
}