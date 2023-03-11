package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.DriveCommands;
import frc.robot.commands.DumpyCommands;
import frc.robot.commands.ElevatorCommands;
import frc.robot.commands.GrabbyCommands;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.DumpySub;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.subsystems.GrabbySub;
import frc.robot.subsystems.PigeonSub;
public final class AutoCommands {
  private AutoCommands() {
  }
  
  public static Command stableize(DriveSub driveSub, PigeonSub pigeonSub){
    return new Stableize(driveSub, pigeonSub);
  }
  public static Command chargeStation(DriveSub drivesub, PigeonSub pigeonSub){
    return ((DriveCommands.arcadeDrive(drivesub, -.35, 0).withTimeout(5.5)).until(()-> pigeonSub.getPitch() >= 8)).andThen(stableize(drivesub, pigeonSub));
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
  return DumpyCommands.dumpyToAnglePID(dumpySub, 0);
 }

 public static Command runDumpy(DumpySub dumpSub, Double speed){
  return DumpyCommands.rotateDumpy(dumpSub, speed);
 }

 public static Command dumpyDown(DumpySub dumpySub){
  return DumpyCommands.dumpyToAnglePID(dumpySub, 90);
 }

 private static Command WaitCommand(double d) {
  return new WaitCommand(d);
}

 //Elevator Commands
 public static Command toggleElevator( ElevatorSub elevatorSub){
  return ElevatorCommands.elevatorToggle(elevatorSub);
 }
 public static Command runToLength(ElevatorSub elevatorSub, Double length, Double tolorance){
  return ElevatorCommands.runToLength(elevatorSub, length, tolorance);
 }

 public static Command toggleClaw(GrabbySub grabbySub){
  return GrabbyCommands.grabThingy(grabbySub);
 }

 public static Command runToLengthAndDrop(  ElevatorSub elevatorSub, DumpySub dumpySub, GrabbySub grabbySub, Double length, Double tolorance){
  return 
  (runToLength(elevatorSub, length, tolorance)
    .andThen((runDumpy(dumpySub, -.5)).withTimeout(.5))
    .andThen(WaitCommand(1))
      .andThen(toggleClaw(grabbySub))
        .andThen(WaitCommand(1)) 
      .andThen(toggleClaw(grabbySub)))
  .andThen((runDumpy(dumpySub, .5).withTimeout(.5)))
  .andThen(ElevatorCommands.runToLength(elevatorSub, -.5, .1));
 }

 public static Command fullAuto(DriveSub  driveSub, PigeonSub  pigeonSub, ElevatorSub elevatorSub, GrabbySub grabbySub, DumpySub dumpySub){
  return
    toggleElevator(elevatorSub)
   .andThen(WaitCommand(1.5))
   .andThen(runToLengthAndDrop(elevatorSub, dumpySub, grabbySub, -4.8,.1))
  .andThen(chargeStation( driveSub,  pigeonSub));
  }




}