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
    
    //Public finals :)
    private final NetworkTable m_networktable = NetworkTableInstance.getDefault().getTable(getName());
    private final NetworkTableEntry m_setlowgEntry = m_networktable.getEntry("lowgear");
    private final Logger m_logger = Logger.getLogger(getName());
    private final WPI_TalonFX m_left1; 
    private final WPI_TalonFX m_right1;
    private final WPI_TalonFX m_left2;
    private final WPI_TalonFX m_right2;
    private final MotorControllerGroup m_left;
    private final MotorControllerGroup m_right;

    private final DoubleSolenoid m_shifter;

    public DriveSub() {
        //Construct them bad bois

        //Motors here, name them something good like frontLeft, ect ect. 
        m_left1 = new WPI_TalonFX(Constants.DrivetrainConstants.DRIVE_LEFT_1_ID);
        m_left2 = new WPI_TalonFX(Constants.DrivetrainConstants.DRIVE_LEFT_2_ID);
        m_right1 = new WPI_TalonFX(Constants.DrivetrainConstants.DRIVE_RIGHT_1_ID);
        m_right2 = new WPI_TalonFX(Constants.DrivetrainConstants.DRIVE_RIGHT_2_ID);
        m_shifter = new DoubleSolenoid(
            PneumaticsModuleType.CTREPCM,
            Constants.DrivetrainConstants.SHIFTER_FORWARD_CHANNEL, 
            Constants.DrivetrainConstants.SHIFTER_REVERSE_CHANNEL
        );

        m_left = new MotorControllerGroup(m_left1, m_left2);
        m_right = new MotorControllerGroup(m_right1, m_right2);
        m_right.setInverted(true);

        leftEncoder = new Encoder(DrivetrainConstants.LEFT_ENCODER_1,DrivetrainConstants.LEFT_ENCODER_2);
        rightEncoder = new Encoder(DrivetrainConstants.RIGHT_ENCODER_1,DrivetrainConstants.RIGHT_ENCODER_2);

        leftEncoder.setDistancePerPulse(DrivetrainConstants.DRIVETRAIN_DISTANCE_PER_PULSE);
        rightEncoder.setDistancePerPulse(DrivetrainConstants.DRIVETRAIN_DISTANCE_PER_PULSE);

    }

    public void arcadeDrive(double move, double turn) {
        WheelSpeeds wheelSpeeds = DifferentialDrive.arcadeDriveIK(move, turn, true);
        m_left.set(wheelSpeeds.left);
        m_right.set(wheelSpeeds.right); 
        // TODO
    }
    
    public void setLowGear(boolean lowGear) {
        m_setlowgEntry.setBoolean(lowGear);
        DoubleSolenoid.Value a = lowGear ? DrivetrainConstants.LOW_GEAR_VALUE : DrivetrainConstants.HIGH_GEAR_VALUE;
        // <condition> ? <expression 1> : <expression 2>
        m_shifter.set(a);
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
        return m_shifter.get() == DrivetrainConstants.LOW_GEAR_VALUE;
    }
    
}
