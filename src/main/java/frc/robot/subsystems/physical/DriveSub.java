
package frc.robot.subsystems.physical;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.Constants;
import frc.robot.subsystems.interfaces.Drivetrain;

public class DriveSub implements Drivetrain {
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
        frontLeft = new WPI_TalonFX(Constants.DrivetrainConstants.driveFrontLeftID);
        backLeft = new WPI_TalonFX(Constants.DrivetrainConstants.driveBackLeftID);
        frontRight = new WPI_TalonFX(Constants.DrivetrainConstants.driveFrontRightID);
        backRight = new WPI_TalonFX(Constants.DrivetrainConstants.driveBackRightID);

        left = new MotorControllerGroup(frontLeft, backLeft);
        right = new MotorControllerGroup(frontRight, backRight);

    }

    @Override
    public void arcadeDrive(double move, double turn) {
        WheelSpeeds wheelSpeeds = DifferentialDrive.arcadeDriveIK(move, turn, true);
        left.set(wheelSpeeds.left);
        right.set(wheelSpeeds.right); 
        // TODO
    }

}
