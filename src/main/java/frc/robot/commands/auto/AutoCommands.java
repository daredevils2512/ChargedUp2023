package frc.robot.commands.auto;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveCommands;
import frc.robot.commands.DumpyCommands;
import frc.robot.commands.ElevatorCommands;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.DumpySub;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.subsystems.PigeonSub;
import frc.robot.utils.Constants.DrivetrainConstants;
import frc.robot.utils.Constants.DumpyConstants;

public final class AutoCommands {
  private AutoCommands() {
  }
  
  public static Command stableize(DriveSub driveSub, PigeonSub pigeonSub){
    return new Stableize(driveSub, pigeonSub);
  }
  public static Command chargeStation(DriveSub drivesub, PigeonSub pigeonSub){
    return (DriveCommands.arcadeDrive(drivesub, -.25, 0).until(()-> pigeonSub.getPitch() >= 8)).andThen(stableize(drivesub, pigeonSub)).withTimeout(4);
  }
 public static Command turnToAngle(DriveSub driveSub, PigeonSub pigeonSub, int angleToTurnTO){
    return new TurnToAngle(driveSub, pigeonSub, angleToTurnTO);
  }

  public static Command driveDistance(DriveSub driveSub, double driveTarget, double tolerance, double velocity) {
    return new DriveDistance(driveSub, driveTarget, tolerance, velocity);
  }
  
  // public static Command driveToLength(DriveSub driveSub, double distance, double tolerance){
  //   DoubleSupplier speed = () -> DrivetrainConstants.DRIVETRAIN_SPEED * Math.signum(distance);
  //   return DriveCommands.arcadeDrive(driveSub, speed, () -> 0).until(() -> driveSub.getLeftDistance() - distance <= tolerance);
  // }    

 //Dumpy commands
 public static Command dumpyUp(DumpySub dumpySub){
  return DumpyCommands.dumpyToAnglePID(dumpySub, DumpyConstants.DUMPY_UP);
 }

 public static Command dumpyDown(DumpySub dumpySub){
  return DumpyCommands.dumpyToAnglePID(dumpySub, 0);
 }


 //Elevator Commands
 public static Command toggleElevator( ElevatorSub elevatorSub){
  return ElevatorCommands.elevatorToggle(elevatorSub);
 }

 public static Command toggleClaw(DumpySub dumpySub){
  return DumpyCommands.clawGrab(dumpySub);
 }

 public static Command runToLengthAndDrop( ElevatorSub elevatorSub,DumpySub dumpySub, Double length, Double tolorance){
  return ElevatorCommands.runToLength(elevatorSub, length, tolorance).andThen(toggleClaw(dumpySub));
 }

 public static Command fullAuto(DriveSub m_driveSub, PigeonSub m_pigeonSub, ElevatorSub elevatorSub, DumpySub dumpySub){
  return runToLengthAndDrop(elevatorSub, dumpySub, 4.8,.1).andThen((toggleElevator(elevatorSub).andThen(chargeStation(m_driveSub, m_pigeonSub))));
    
  }
}