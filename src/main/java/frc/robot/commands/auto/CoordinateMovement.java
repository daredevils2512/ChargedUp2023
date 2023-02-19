package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.BooleanSupplier;

import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.subsystems.AutoDriveSub;
import frc.robot.utils.Constants.Auto;

public class CoordinateMovement extends CommandBase{

  private final AutoDriveSub auto_drive;
  private final RamseteController ramseteControl;
  private Rotation2d goalRotation;
  private Translation2d goalTranslation;
  private Pose2d goalPose;

  public CoordinateMovement(double targetX, double targetY, double targetAngleDegrees, AutoDriveSub drivetrain) {
    
    auto_drive = drivetrain;

    goalRotation = new Rotation2d(targetAngleDegrees * Math.PI / 180);
    goalTranslation = new Translation2d(targetX, targetY);
    goalPose = new Pose2d(goalTranslation, goalRotation);

    ramseteControl = new RamseteController(); 

    Rotation2d rotationTolerance = new Rotation2d(Auto.AUTO__DEGREES_ERROR * Math.PI / 180);
    Translation2d transTolerance = new Translation2d(Auto.AUTO_ERROR, Auto.AUTO_ERROR);
    Pose2d poseTolerance = new Pose2d(transTolerance, rotationTolerance);
    ramseteControl.setTolerance(poseTolerance);

    addRequirements(drivetrain);
  
  }
  
@Override
  public void initialize() {

  }

  /** Called every time the scheduler runs while the command is scheduled. */
  @Override
  public void execute() {
    ChassisSpeeds driveSpeed = ramseteControl.calculate(auto_drive.getRobotPosition(), goalPose,
    auto_drive.currentChassisSpeeds().vxMetersPerSecond, auto_drive.currentChassisSpeeds().omegaRadiansPerSecond);

    auto_drive.useChassisSpeeds(driveSpeed);
  }

  /** Called once the command ends or is interrupted. */
  @Override
  public void end(boolean interrupted) {
    auto_drive.clearSpeed();
  }

  /** Returns true when the command should end. */
  @Override
  public boolean isFinished() {
    BooleanSupplier reachedPosition = () -> ramseteControl.atReference();
    return reachedPosition.getAsBoolean();
  }
}