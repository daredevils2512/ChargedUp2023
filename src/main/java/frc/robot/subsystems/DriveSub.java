package frc.robot.subsystems;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;
import frc.robot.utils.Constants.DrivetrainConstants;

public class DriveSub extends SubsystemBase {
  // Public finals :)
  private final NetworkTable m_networktable = NetworkTableInstance.getDefault().getTable(getName());
  private final NetworkTableEntry m_setlowgEntry = m_networktable.getEntry("lowgear");
  private final NetworkTableEntry leftDistance = m_networktable.getEntry("left distance");
  private final NetworkTableEntry rightDistance = m_networktable.getEntry("right distance");
  private final NetworkTableEntry leftSpeed = m_networktable.getEntry("left speed");
  private final NetworkTableEntry rightSpeed = m_networktable.getEntry("right speed");

  
  private final Logger m_logger = Logger.getLogger(getName());
  private final DoubleSolenoid shifter;

  private final WPI_TalonFX frontLeft;
  private final WPI_TalonFX frontRight;
  private final WPI_TalonFX backLeft;
  private final WPI_TalonFX backRight;
  private final MotorControllerGroup left;
  private final MotorControllerGroup right;
  private final Encoder leftEncoder;
  private final Encoder rightEncoder;

  public DriveSub() {

    frontLeft = new WPI_TalonFX(DrivetrainConstants.DRIVE_FRONT_LEFT_ID);
    backLeft = new WPI_TalonFX(DrivetrainConstants.DRIVE_BACK_LEFT_ID);
    frontRight = new WPI_TalonFX(DrivetrainConstants.DRIVE_FRONT_RIGHT_ID);
    backRight = new WPI_TalonFX(DrivetrainConstants.DRIVE_BACK_RIGHT_ID);
    shifter = new DoubleSolenoid(
      PneumaticsModuleType.CTREPCM,
      Constants.DrivetrainConstants.SHIFTER_FORWARD_CHANNEL, 
      Constants.DrivetrainConstants.SHIFTER_REVERSE_CHANNEL
    );
    left = new MotorControllerGroup(frontLeft, backLeft);
    right = new MotorControllerGroup(frontRight, backRight);
    right.setInverted(true);

    leftEncoder = new Encoder(DrivetrainConstants.DRIVETRAIN_LEFT_ENCODER_A,
        DrivetrainConstants.DRIVETRAIN_LEFT_ENCODER_B);
    rightEncoder = new Encoder(DrivetrainConstants.DRIVETRAIN_RIGHT_ENCODER_A,
        DrivetrainConstants.DRIVETRAIN_RIGHT_ENCODER_B);
    
    leftEncoder.setDistancePerPulse(DrivetrainConstants.DISTANCE_PER_PULSE);
    rightEncoder.setDistancePerPulse(DrivetrainConstants.DISTANCE_PER_PULSE);

  }

  public void arcadeDrive(double move, double turn) {
    WheelSpeeds wheelSpeeds = DifferentialDrive.arcadeDriveIK(move, turn, true);
    left.set(wheelSpeeds.left);
    right.set(wheelSpeeds.right);
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

  public void toggleShifters() {
      setLowGear(!getLowGear());
  }

  @Override
  public void periodic() {
    leftDistance.setDouble(getLeftDistance());
    rightDistance.setDouble(getRightDistance());
    leftSpeed.setDouble(getLeftSpeed());
    rightSpeed.setDouble(getRightSpeed());

  }

}
