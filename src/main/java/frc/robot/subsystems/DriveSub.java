package frc.robot.subsystems;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;
import frc.robot.utils.Constants.DrivetrainConstants;

public class DriveSub extends SubsystemBase {
    //Private finals :)
    private final Encoder leftEncoder;
    private final Encoder rightEncoder;
    
    private final NetworkTable m_networktable = NetworkTableInstance.getDefault().getTable(getName());
    private final NetworkTableEntry m_setlowgEntry = m_networktable.getEntry("lowgear");
    private final Logger m_logger = Logger.getLogger(getName());
    private final WPI_TalonFX frontLeft; 
    private final WPI_TalonFX frontRight;
    private final WPI_TalonFX backLeft;
    private final WPI_TalonFX backRight;
    private final MotorControllerGroup left;
    private final MotorControllerGroup right;

    private final DoubleSolenoid shifter;

  public DriveSub() {

        //Motors here, name them something good like frontLeft, ect ect. 
        frontLeft = new WPI_TalonFX(Constants.DrivetrainConstants.DRIVE_LEFT_1_ID);
        backLeft = new WPI_TalonFX(Constants.DrivetrainConstants.DRIVE_LEFT_2_ID);
        frontRight = new WPI_TalonFX(Constants.DrivetrainConstants.DRIVE_RIGHT_1_ID);
        backRight = new WPI_TalonFX(Constants.DrivetrainConstants.DRIVE_RIGHT_2_ID);
        shifter = new DoubleSolenoid(
            PneumaticsModuleType.CTREPCM,
            Constants.DrivetrainConstants.SHIFTER_FORWARD_CHANNEL, 
            Constants.DrivetrainConstants.SHIFTER_REVERSE_CHANNEL
        );

        left = new MotorControllerGroup(frontLeft, backLeft);
        right = new MotorControllerGroup(frontRight, backRight);
        right.setInverted(true);

        leftEncoder = new Encoder(DrivetrainConstants.DRIVETRAIN_LEFT_ENCODER_1,DrivetrainConstants.DRIVETRAIN_LEFT_ENCODER_2);
        rightEncoder = new Encoder(DrivetrainConstants.DRIVETRAIN_RIGHT_ENCODER_1,DrivetrainConstants.DRIVETRAIN_RIGHT_ENCODER_2);

        leftEncoder.setDistancePerPulse(DrivetrainConstants.DRIVETRAIN_DISTANCE_PER_PULSE);
        rightEncoder.setDistancePerPulse(DrivetrainConstants.DRIVETRAIN_DISTANCE_PER_PULSE);

  }

    public void arcadeDrive(double move, double turn) {
        WheelSpeeds wheelSpeeds = DifferentialDrive.arcadeDriveIK(move, turn, true);
        left.set(wheelSpeeds.left);
        right.set(wheelSpeeds.right); 
        // TODO
    }
    
    public void setLowGear(boolean lowGear) {
        m_setlowgEntry.setBoolean(lowGear);
        DoubleSolenoid.Value a = lowGear ? DrivetrainConstants.LOW_GEAR_VALUE : DrivetrainConstants.HIGH_GEAR_VALUE;
        // <condition> ? <expression 1> : <expression 2>
        shifter.set(a);
        m_logger.info("set low gear" + lowGear);
    }

   //Encoder functions
    public int getLeftEncoder() {
        return leftEncoder.get();
    }

    public int getRightEncoder() {
        return rightEncoder.get();
    }

    public double getLeftDistance() {
        return leftEncoder.getDistance();
    }

    public double getRightDistance() {
        return rightEncoder.getDistance();
    }

    public double getLeftSpeed() {
        return leftEncoder.getRate();
    }

    public double getRightSpeed() {
        return rightEncoder.getRate();
    }

    public double getDistance() {
      return (getLeftDistance() + getRightDistance()) / 2;
    }


    public Boolean getLowGear() {
        return shifter.get() == DrivetrainConstants.LOW_GEAR_VALUE;
    }
    
}
