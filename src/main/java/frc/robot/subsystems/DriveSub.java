package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;

public class DriveSub extends SubsystemBase {
    //Public finals :)
    private final WPI_TalonFX frontLeft; 
    private final WPI_TalonFX frontRight;
    private final WPI_TalonFX backLeft;
    private final WPI_TalonFX backRight;
    private final MotorControllerGroup left;
    private final MotorControllerGroup right;

    public DriveSub() {
        //Construct them bad bois

        //Motors here, name them something good like frontLeft, ect ect. 
        frontLeft = new WPI_TalonFX(Constants.DrivetrainConstants.DRIVE_FRONT_RIGHT_ID);
        backLeft = new WPI_TalonFX(Constants.DrivetrainConstants.DRIVE_BACK_RIGHT_ID);
        frontRight = new WPI_TalonFX(Constants.DrivetrainConstants.DRIVE_FRONT_LEFT_ID);
        backRight = new WPI_TalonFX(Constants.DrivetrainConstants.DRIVE_BACK_LEFT_ID);

        left = new MotorControllerGroup(frontLeft, backLeft);
        right = new MotorControllerGroup(frontRight, backRight);

    }

    public void arcadeDrive(double move, double turn) {
        WheelSpeeds wheelSpeeds = DifferentialDrive.arcadeDriveIK(move, turn, true);
        left.set(wheelSpeeds.left);
        right.set(wheelSpeeds.right); 
        // TODO
    }

}
