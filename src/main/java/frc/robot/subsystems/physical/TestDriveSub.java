package frc.robot.subsystems.physical;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.subsystems.interfaces.Drivetrain;
import frc.robot.utils.Constants;

public class TestDriveSub implements Drivetrain{
    private final WPI_VictorSPX frontLeft; 
    private final WPI_VictorSPX backLeft; 
    private final WPI_VictorSPX frontRight; 
    private final WPI_VictorSPX backRight; 
    private final MotorControllerGroup left; 
    private final MotorControllerGroup right;
    private final Encoder leftEncoder;
    private final Encoder rightEncoder;

    public TestDriveSub () {
        frontLeft = new WPI_VictorSPX(Constants.DRIVETRAIN_FRONT_LEFT_ID);
        backLeft = new WPI_VictorSPX(Constants.DRIVETRAIN_BACK_LEFT_ID);
        frontRight = new WPI_VictorSPX (Constants.DRIVETRAIN_FRONT_RIGHT_ID);
        backRight = new WPI_VictorSPX (Constants.DRIVETRAIN_BACK_RIGHT_ID); 

        leftEncoder = new Encoder(0, 0);
        rightEncoder = new Encoder (0, 0);
        leftEncoder.setReverseDirection(true);
        left = new MotorControllerGroup (frontLeft, backLeft);
        right = new MotorControllerGroup (frontRight, backRight);
    }

    @Override
    public void arcadeDrive(double move, double turn) {
        WheelSpeeds wheelSpeeds = DifferentialDrive.arcadeDriveIK(move, turn, false);
        left.set(wheelSpeeds.left);
        right.set(wheelSpeeds.right);
    }
}