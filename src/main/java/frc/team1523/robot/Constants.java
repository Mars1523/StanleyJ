package frc.team1523.robot;

public final class Constants {

    public static final class DriveConstants {
        public static final int kEncoderCPR = 1024;
        public static final double kWheelDiameterInches = 4;
        public static final double kEncoderDistancePerPulse =
                // Assumes the encoders are directly mounted on the wheel shafts
                (kWheelDiameterInches * Math.PI) / (double) kEncoderCPR;
    }
}
