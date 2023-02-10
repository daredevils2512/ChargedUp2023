package frc.robot.commands.auto;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AutoDriveSub;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.PigeonSub;


public final class AutoCommands {

  private AutoCommands(AutoDriveSub drivetrain) { 

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

  /** There's no code to create trajectories yet - in theory that's what we could do with Pathweaver.
  * Don't know where in the code to incorporate it though.
  */
  public static Command trajectoryMovement(AutoDriveSub auto_drive, Trajectory path){
   return new TrajectoryMovement(auto_drive, path);
  }

 public static Command turnToAngle(DriveSub driveSub, PigeonSub pigeonSub, Integer angleToTurnTO){
    return new TurnToAngle(driveSub, pigeonSub, angleToTurnTO);
 }

 public static Command fullAuto(){
   return null;
   }
}