package frc.team1523.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class LiftSubsystem extends PIDSubsystem {
    private final WPI_TalonSRX master = new WPI_TalonSRX(2);
    private final WPI_TalonSRX follower1 = new WPI_TalonSRX(3);
    private final WPI_TalonSRX follower2 = new WPI_TalonSRX(4);

    private final Encoder encoder = new Encoder(0, 1);

    public LiftSubsystem() {
        super(new PIDController(0.022, 0.0, 0.0));
        follower1.follow(master);
        follower2.follow(master);
    }

    public double getSetpoint() {
        return this.getController().getSetpoint();
    }

    @Override
    public void setSetpoint(double setpoint) {
        super.setSetpoint(setpoint);
    }

    public boolean atSetpoint() {
        return this.getController().atSetpoint();
    }

    @Override
    protected void useOutput(double output, double setpoint) {
        master.set(ControlMode.PercentOutput, output);
    }

    @Override
    protected double getMeasurement() {
        return encoder.getDistance();
    }
}
