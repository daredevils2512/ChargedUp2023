package frc.robot.commands.auto;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveCommands;
import frc.robot.commands.DumpyCommands;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.DumpySub;
import frc.robot.subsystems.PigeonSub;
import frc.robot.utils.Constants.DrivetrainConstants;
import frc.robot.utils.Constants.DumpyConstants;

public final class AutoCommands {
  private AutoCommands() {
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