package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.BooleanSupplier;

import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.utils.Constants;
import frc.robot.subsystems.OdometrySub;

public class RobotMovementTest extends CommandBase{

  private final OdometrySub auto_drivetrain;
  private final RamseteController ramseteControl;
  private Rotation2d goalRotation;
  private Translation2d goalTranslation;
  private Pose2d goalPose;

  public RobotMovementTest(double targetX, double targetY, double targetAngleDegrees, OdometrySub odometer) {
    
    auto_drivetrain = odometer;

    goalRotation = new Rotation2d(targetAngleDegrees * 180 / Math.PI);
    goalTranslation = new Translation2d(targetX, targetY);
    goalPose = new Pose2d(goalTranslation, goalRotation);

    ramseteControl = new RamseteController(); 

    addRequirements(odometer);
  
  }
@Override
  public void initialize() {  

  }

  /** Called every time the scheduler runs while the command is scheduled. */
  @Override
  public void execute() {
    ChassisSpeeds driveSpeed = ramseteControl.calculate(auto_drivetrain.getRobotPosition(), goalPose,
    auto_drivetrain.currentChassisSpeeds().vxMetersPerSecond,  auto_drivetrain.currentChassisSpeeds().omegaRadiansPerSecond);

    auto_drivetrain.useChassisSpeeds(driveSpeed);
  }

  /** Called once the command ends or is interrupted. */
  @Override
  public void end(boolean interrupted) {
    auto_drivetrain.clearSpeed();
  }

  /** Returns true when the command should end. */
  @Override
  public boolean isFinished() {
    BooleanSupplier reachedPosition = () -> Math.abs(auto_drivetrain.getRobotPosition().getX() - goalPose.getX()) < Constants.AUTO_ERROR 
    && Math.abs(auto_drivetrain.getRobotPosition().getY() - goalPose.getY()) < Constants.AUTO_ERROR 
    && Math.abs(auto_drivetrain.getRobotPosition().getRotation().minus(goalPose.getRotation()).getDegrees()) < Constants.AUTO__DEGREES_ERROR;
    return reachedPosition.getAsBoolean();
  }
}