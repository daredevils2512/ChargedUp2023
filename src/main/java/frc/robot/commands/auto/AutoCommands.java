package frc.robot.commands.auto;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveCommands;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.PigeonSub;
import frc.robot.utils.Constants.Auto;
import frc.robot.utils.Constants.DrivetrainConstants;

public final class AutoCommands {
  private AutoCommands() {
  }

  /**
   * Auto command that only drives back.
   * 
   * @param drivetrain    The drivetrain subsystem to use
   * @param speed         The speed to go during autonomous (should be negative to
   *                      go backwards)
   * @param driveDistance The distance to drive backwards
   * @return The command to be used when called.
   */

  public static Command turnToAngle(DriveSub driveSub, PigeonSub pigeonSub, int angleToTurnTO) {
    return new TurnToAngle(driveSub, pigeonSub, angleToTurnTO);
  }

  public static Command driveDistance(DriveSub driveSub, double driveTarget, double tolerance, double velocity) {
    return new DriveDistance(driveSub, driveTarget, tolerance, velocity);
  }
  
  public static Command driveToLength(DriveSub driveSub, double distance, double tolerance){
    DoubleSupplier speed = () -> DrivetrainConstants.DRIVETRAIN_SPEED * Math.signum(distance);
    return DriveCommands.arcadeDrive(driveSub, speed, () -> 0).until(() -> driveSub.getLeftDistance() - distance <= tolerance);
  }    

  public static Command fullAuto() {
    return null;
  }
}