package frc.team1523.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team1523.robot.subsystems.LiftSubsystem;

public class SetElevatorSetpoint extends CommandBase {
    private final LiftSubsystem liftSubsystem;
    private final double setpoint;

    public SetElevatorSetpoint(LiftSubsystem liftSubsystem, double setpoint) {
        this.liftSubsystem = liftSubsystem;
        this.setpoint = setpoint;
        addRequirements(liftSubsystem);
    }

    @Override
    public void initialize() {
        liftSubsystem.setSetpoint(setpoint);
        liftSubsystem.enable();
    }

    @Override
    public boolean isFinished() {
        return liftSubsystem.atSetpoint();
    }
}
