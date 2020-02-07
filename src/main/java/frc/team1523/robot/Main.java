package frc.team1523.robot;

import edu.wpi.first.wpilibj.RobotBase;

// All robot initialization should go in Robot.java
public final class Main {
    public static void main(String... args) {
        RobotBase.startRobot(Robot::new);
    }
}
