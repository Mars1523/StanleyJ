package frc.team1523.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;

import static frc.team1523.robot.Constants.DriveConstants.kEncoderDistancePerPulse;

public class DriveSubsystem extends SubsystemBase {
    private final SpeedController leftMotor = new Spark(0);
    private final SpeedController rightMotor = new Spark(1);

    private final Encoder leftEncoder = new Encoder(2,3);
    private final Encoder rightEncoder = new Encoder(4,5,true);

    private final DifferentialDrive driveTrain = new DifferentialDrive(leftMotor, rightMotor);

    private final DoubleSupplier speedMultiplier;

    public DriveSubsystem(DoubleSupplier speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
        leftEncoder.setDistancePerPulse(kEncoderDistancePerPulse);
        rightEncoder.setDistancePerPulse(kEncoderDistancePerPulse);
    }

    public void arcadeDrive(double fwd, double rot) {
        driveTrain.arcadeDrive(fwd * speedMultiplier.getAsDouble(), rot);
    }

    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    public Encoder getLeftEncoder() {
        return leftEncoder;
    }

    public Encoder getRightEncoder() {
        return rightEncoder;
    }
}
