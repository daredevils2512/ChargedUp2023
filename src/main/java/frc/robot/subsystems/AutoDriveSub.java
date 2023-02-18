package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.WPI_Pigeon2;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;
import frc.robot.utils.Constants.Auto;
import frc.robot.utils.Constants.AutoDriveConstants;
import frc.robot.utils.Constants.DrivetrainConstants;


public class AutoDriveSub extends SubsystemBase {
    private final WPI_TalonFX frontLeft; 
    private final WPI_TalonFX frontRight;
    private final WPI_TalonFX backLeft;
    private final WPI_TalonFX backRight;
    private final MotorControllerGroup left;
    private final MotorControllerGroup right;
    private final Encoder leftEncoder;
    private final Encoder rightEncoder;
    private final DoubleSolenoid m_shifter;

    private final NetworkTable table;
    private final NetworkTableEntry robotX;
    private final NetworkTableEntry robotY;
    private final NetworkTableEntry robotTurn;
    private final NetworkTableEntry linearVelocity;
    private final NetworkTableEntry angularVelocity;

    private final WPI_Pigeon2 pigeon;
    private final DifferentialDriveOdometry odometry;
    private final SimpleMotorFeedforward feedforward;
    private final PIDController leftPID;
    private final PIDController rightPID;
  
    public AutoDriveSub() {
        //Construct them bad bois
        table = NetworkTableInstance.create().getTable("Odometry");
        robotX = table.getEntry("X-coordinate");
        robotY = table.getEntry("Y-coordinate");
        robotTurn = table.getEntry("Turn angle");
        linearVelocity = table.getEntry("Linear velocity");
        angularVelocity = table.getEntry("Angular velocity");

        //Motors here, name them something good like frontLeft, ect ect. 
        frontLeft = new WPI_TalonFX(Constants.DrivetrainConstants.DRIVE_LEFT_1_ID);
        backLeft = new WPI_TalonFX(Constants.DrivetrainConstants.DRIVE_LEFT_2_ID);
        frontRight = new WPI_TalonFX(Constants.DrivetrainConstants.DRIVE_RIGHT_1_ID);
        backRight = new WPI_TalonFX(Constants.DrivetrainConstants.DRIVE_RIGHT_2_ID);
        m_shifter = new DoubleSolenoid(
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

        pigeon = new WPI_Pigeon2(Auto.PIGEON_PORT);
        
        odometry = new DifferentialDriveOdometry(
            pigeon.getRotation2d(), getLeftDistance(), getRightDistance()
        );

        feedforward = new SimpleMotorFeedforward(AutoDriveConstants.FEEDFORWARD_KS, AutoDriveConstants.FEEDFORWARD_KV, AutoDriveConstants.FEEDFORWARD_KA);
        
        leftPID = new PIDController(AutoDriveConstants.AUTO_DRIVE_KP, AutoDriveConstants.AUTO_DRIVE_KI, AutoDriveConstants.AUTO_DRIVE_KD);
        rightPID = new PIDController(AutoDriveConstants.AUTO_DRIVE_KP, AutoDriveConstants.AUTO_DRIVE_KI, AutoDriveConstants.AUTO_DRIVE_KD);

    }

    public void setLowGear(boolean lowGear) {
        DoubleSolenoid.Value a = lowGear ? DrivetrainConstants.LOW_GEAR_VALUE : DrivetrainConstants.HIGH_GEAR_VALUE;
        // <condition> ? <expression 1> : <expression 2>
        m_shifter.set(a);
    }

    public Boolean getLowGear() {
        return m_shifter.get() == DrivetrainConstants.LOW_GEAR_VALUE;
    }

    //Encoder Functions
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

    //Gyro function
    public double getYaw() {
        return pigeon.getYaw();
    }

    //Driving functions
    public ChassisSpeeds currentChassisSpeeds() {
        var wheelspeeds = new DifferentialDriveWheelSpeeds(getLeftSpeed(), getRightSpeed());
        return AutoDriveConstants.kinematics.toChassisSpeeds(wheelspeeds);
    }

    public void useChassisSpeeds(ChassisSpeeds chassisSpeeds) {
        DifferentialDriveWheelSpeeds wheelSpeeds = AutoDriveConstants.kinematics.toWheelSpeeds(chassisSpeeds);
        double leftVelocity = feedforward.calculate(wheelSpeeds.leftMetersPerSecond);
        double rightVelocity = feedforward.calculate(wheelSpeeds.rightMetersPerSecond);

        double leftOutput = leftPID.calculate(getLeftSpeed(), wheelSpeeds.leftMetersPerSecond);
        double rightOutput = rightPID.calculate(getRightSpeed(), wheelSpeeds.rightMetersPerSecond);

        left.setVoltage(leftVelocity + leftOutput);
        right.setVoltage(rightVelocity + rightOutput);
    }

    public void useWheelSpeeds(double leftVelocity, double rightVelocity) {
        double leftVoltage = feedforward.calculate(leftVelocity);
        double rightVoltage = feedforward.calculate(rightVelocity);

        double leftOutput = leftPID.calculate(getLeftSpeed(), leftVelocity);
        double rightOutput = rightPID.calculate(getRightSpeed(), rightVelocity);

        left.setVoltage(leftVoltage + leftOutput);
        right.setVoltage(rightVoltage + rightOutput);
    }

    public void clearSpeed() {
        left.set(0);
        right.set(0);
    }

    //Odometry functions
    public Pose2d updateOdometry() {
        return odometry.update(pigeon.getRotation2d(), getLeftDistance(), getRightDistance());
    }

    public Pose2d getRobotPosition() {
        return odometry.getPoseMeters();
    }

    @Override
    public void periodic() {
        updateOdometry();
        robotX.setNumber(odometry.getPoseMeters().getX());
        robotY.setNumber(odometry.getPoseMeters().getY());
        robotTurn.setNumber(odometry.getPoseMeters().getRotation().getDegrees());
        linearVelocity.setNumber(currentChassisSpeeds().vxMetersPerSecond);
        angularVelocity.setNumber(currentChassisSpeeds().omegaRadiansPerSecond);
    }
}
