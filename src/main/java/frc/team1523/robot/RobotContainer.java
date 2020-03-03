package frc.team1523.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team1523.robot.commands.ManualLiftControl;
import frc.team1523.robot.commands.SetElevatorSetpoint;
import frc.team1523.robot.subsystems.DriveSubsystem;
import frc.team1523.robot.subsystems.IntakeSubsystem;
import frc.team1523.robot.subsystems.LiftSubsystem;
import frc.team1523.robot.subsystems.LimelightSubsystem;

public class RobotContainer {
    private static final String kDefaultAuto = "Default";
    private static final String kCustomAuto = "My Auto";
    private final SendableChooser<String> chooser = new SendableChooser<>();

    private final XboxController primaryGamepad = new XboxController(0);
    private final XboxController altGamepad = new XboxController(1);

    private final LiftSubsystem liftSubsystem = new LiftSubsystem();
    private final DriveSubsystem driveSubsystem = new DriveSubsystem(() -> {
        // Reduce the speed of the drive train if the lift is up
        if (liftSubsystem.getSetpoint() > 700) {
            return 0.8;
        } else if (liftSubsystem.getSetpoint() > 1500) {
            return 0.8 * 0.8;
        } else {
            return 1;
        }
    });
    private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    private final LimelightSubsystem limelightSubsystem = new LimelightSubsystem();

    public RobotContainer() {
        configureButtonBindings();

        chooser.setDefaultOption("Default Auto", kDefaultAuto);
        chooser.addOption("My Auto", kCustomAuto);
        Shuffleboard.getTab("Drive").add("Auto choicess", chooser);

        limelightSubsystem.disableLeds();

        driveSubsystem.setDefaultCommand(
                new RunCommand(() -> driveSubsystem.arcadeDrive(
                        primaryGamepad.getTriggerAxis(GenericHID.Hand.kLeft) +
                                -primaryGamepad.getTriggerAxis(GenericHID.Hand.kRight),
                        primaryGamepad.getX(GenericHID.Hand.kLeft) * .75),
                        driveSubsystem));

        liftSubsystem.setDefaultCommand(
                new ManualLiftControl(
                        liftSubsystem,
                        () -> (primaryGamepad.getTriggerAxis(GenericHID.Hand.kRight)
                                - primaryGamepad.getTriggerAxis(GenericHID.Hand.kLeft)) * 60));

        intakeSubsystem.setDefaultCommand(new RunCommand(
                () -> intakeSubsystem.setSpeed(-altGamepad.getY(GenericHID.Hand.kRight)),
                intakeSubsystem));
    }

    private void configureButtonBindings() {
        new JoystickButton(altGamepad, XboxController.Button.kA.value)
                .whenPressed(new SetElevatorSetpoint(liftSubsystem, 0));
        new JoystickButton(altGamepad, XboxController.Button.kX.value)
                .whenPressed(new SetElevatorSetpoint(liftSubsystem, 600));
        new JoystickButton(altGamepad, XboxController.Button.kY.value)
                .whenPressed(new SetElevatorSetpoint(liftSubsystem, 1600));
        new JoystickButton(altGamepad, XboxController.Button.kB.value)
                .whenPressed(new SetElevatorSetpoint(liftSubsystem, 1800));

        new JoystickButton(altGamepad, XboxController.Button.kBumperRight.value)
                .whenPressed(new InstantCommand(intakeSubsystem::grab));
        new JoystickButton(altGamepad, XboxController.Button.kBumperLeft.value)
                .whenPressed(new InstantCommand(intakeSubsystem::release));
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

    public void disabledInit() {
        limelightSubsystem.disableLeds();
    }

    public void teleopInit() {
        limelightSubsystem.enableLeds();
    }
}