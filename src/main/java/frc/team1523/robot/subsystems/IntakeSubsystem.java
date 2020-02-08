package frc.team1523.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private final Spark leftIntake = new Spark(2);
    private final Spark rightIntake = new Spark(3);

    private final DoubleSolenoid grabber = new DoubleSolenoid(1, 0,1);

    public IntakeSubsystem() {
        rightIntake.setInverted(true);
    }

    public void setSpeed(double speed) {
        leftIntake.set(speed);
        rightIntake.set(speed);
    }

    public void grab() {
        grabber.set(DoubleSolenoid.Value.kReverse);
    }

    public void release() {
        grabber.set(DoubleSolenoid.Value.kForward);
    }

    public void setState(DoubleSolenoid.Value value) {
        grabber.set(value);
    }
}
