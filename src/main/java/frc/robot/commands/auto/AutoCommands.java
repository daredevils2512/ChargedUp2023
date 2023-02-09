package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DumpyCommands;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.DumpySub;
import frc.robot.subsystems.PigeonSub;
import frc.robot.utils.Constants.DumpyConstants;



public final class AutoCommands {
  private AutoCommands() { }
  
  /** Auto command that only drives back.
  * @param drivetrain The drivetrain subsystem to use
  * @param speed The speed to go during autonomous (should be negative to go backwards)
  * @param driveDistance The distance to drive backwards
  * @return The command to be used when called.
  */

 //Drivetrain commands
 public static Command turnToAngle(DriveSub driveSub, PigeonSub pigeonSub, int angleToTurnTO){
    return new TurnToAngle(driveSub, pigeonSub, angleToTurnTO);
 }

 public static Command driveDistance(DriveSub driveSub, int driveTarget){
   return new DriveDistance(driveSub, driveTarget); //PUSH
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