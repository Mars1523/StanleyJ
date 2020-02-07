package frc.team1523.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1523.robot.subsystems.LiftSubsystem;

import java.util.function.DoubleSupplier;

public class ManualLiftControl extends CommandBase {
    private final LiftSubsystem liftSubsystem;
    private final DoubleSupplier setpointAdjustment;

    public ManualLiftControl(LiftSubsystem liftSubsystem, DoubleSupplier setpointAdjustment) {
        this.liftSubsystem = liftSubsystem;
        this.setpointAdjustment = setpointAdjustment;
        addRequirements(liftSubsystem);
    }

    @Override
    public void initialize() {
        liftSubsystem.setSetpoint(liftSubsystem.getSetpoint() + setpointAdjustment.getAsDouble());
        liftSubsystem.enable();
    }
}
