package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TestDriveSub extends SubsystemBase{
    private final WPI_VictorSPX frontLeft; 
    private final WPI_VictorSPX backLeft; 
    private final WPI_VictorSPX frontRight; 
    private final WPI_VictorSPX backRight; 
    private final MotorControllerGroup left; 
    private final MotorControllerGroup right;
    private final Encoder leftEncoder;
    private final Encoder rightEncoder;

    public TestDriveSub () {
        frontLeft = new WPI_VictorSPX(Constants.DrivetrainConstants.driveFrontLeftID);
        backLeft = new WPI_VictorSPX(Constants.DrivetrainConstants.driveBackLeftID);
        frontRight = new WPI_VictorSPX (Constants.DrivetrainConstants.driveFrontRightID);
        backRight = new WPI_VictorSPX (Constants.DrivetrainConstants.driveBackRightID); 

        leftEncoder = new Encoder(0, 0);
        rightEncoder = new Encoder (0, 0);
        leftEncoder.setReverseDirection(true);
        left = new MotorControllerGroup (frontLeft, backLeft);
        right = new MotorControllerGroup (frontRight, backRight);
    }

    public void arcadeDrive(double move, double turn) {
        WheelSpeeds wheelSpeeds = DifferentialDrive.arcadeDriveIK(move, turn, false);
        left.set(wheelSpeeds.left);
        right.set(wheelSpeeds.right);
    }
}