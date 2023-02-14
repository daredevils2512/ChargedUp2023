package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.PigeonSub;


public final class AutoCommands {
  private AutoCommands() { }
  
  /** Auto command that only drives back.
  * @param drivetrain The drivetrain subsystem to use
  * @param speed The speed to go during autonomous (should be negative to go backwards)
  * @param driveDistance The distance to drive backwards
  * @return The command to be used when called.
  */


 public static Command turnToAngle(DriveSub driveSub, PigeonSub pigeonSub, Integer angleToTurnTO){
    return new TurnToAngle(driveSub, pigeonSub, angleToTurnTO);
 }

 public static Command fullAuto(){
    return null;
    }
}