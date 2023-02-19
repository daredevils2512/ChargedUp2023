package frc.robot.commands.auto;

import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.subsystems.AutoDriveSub;
import java.util.function.DoubleSupplier;

import frc.robot.commands.DriveCommands;
import frc.robot.commands.DumpyCommands;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.DumpySub;
import frc.robot.subsystems.PigeonSub;
import frc.robot.utils.Constants.Auto;
import frc.robot.utils.Constants.AutoDriveConstants;
import frc.robot.utils.Constants.DrivetrainConstants;
import frc.robot.utils.Constants.DumpyConstants;

public final class AutoCommands {

  private AutoCommands() { 

  }
  
  /** Auto command that only drives back.
  * @param drivetrain The drivetrain subsystem to use
  * @param speed The speed to go during autonomous (should be negative to go backwards)
  * @param driveDistance The distance to drive backwards
  * @return The command to be used when called.
  */

  public static Command coordinateMovement(AutoDriveSub auto_drive, double targetX, double targetY, double targetAngleDegrees) {
    return new CoordinateMovement(targetX, targetY, targetAngleDegrees, auto_drive);
  }

  /** If we use this, the paths will be defined in Robot.java.
   * I'm sure we can figure out how to connect it to this command, but I haven't yet.
  */
  public static Command trajectoryMovement(AutoDriveSub auto_drive, Trajectory path){
    var ramseteControl = new RamseteController();

    Rotation2d rotationTolerance = new Rotation2d(Auto.AUTO__DEGREES_ERROR * Math.PI / 180);
    Translation2d transTolerance = new Translation2d(Auto.AUTO_ERROR, Auto.AUTO_ERROR);
    Pose2d poseTolerance = new Pose2d(transTolerance, rotationTolerance);
    ramseteControl.setTolerance(poseTolerance);

    return new RamseteCommand(path, () -> auto_drive.getRobotPosition(), ramseteControl, 
    AutoDriveConstants.kinematics, auto_drive::useWheelSpeeds, auto_drive);
  }

 public static Command turnToAngle(DriveSub driveSub, PigeonSub pigeonSub, int angleToTurnTO){
    return new TurnToAngle(driveSub, pigeonSub, angleToTurnTO);
  }

  public static Command driveDistance(DriveSub driveSub, double driveTarget, double tolerance, double velocity) {
    return new DriveDistance(driveSub, driveTarget, tolerance, velocity);
  }
  
  public static Command driveToLength(DriveSub driveSub, double distance, double tolerance){
    DoubleSupplier speed = () -> DrivetrainConstants.DRIVETRAIN_SPEED * Math.signum(distance);
    return DriveCommands.arcadeDrive(driveSub, speed, () -> 0).until(() -> driveSub.getLeftDistance() - distance <= tolerance);
  }    

 //Dumpy commands
 public static Command dumpyUp(DumpySub dumpySub){
    return DumpyCommands.dumpyToAnglePID(dumpySub, DumpyConstants.DUMPY_UP);
 }

 public static Command dumpyDown(DumpySub dumpySub){
    return DumpyCommands.dumpyToAnglePID(dumpySub, 0);
 }

 public static Command beltSuck(DumpySub dumpySub){
    return DumpyCommands.runBelt(dumpySub, DumpyConstants.AUTO_BELT).withTimeout(DumpyConstants.BELT_TIMER);
 }

 public static Command beltSpit(DumpySub dumpySub){
    return DumpyCommands.runBelt(dumpySub, -DumpyConstants.AUTO_BELT).withTimeout(DumpyConstants.BELT_TIMER);
 }

 public static Command fullAuto(){
    return null; 
  }
}