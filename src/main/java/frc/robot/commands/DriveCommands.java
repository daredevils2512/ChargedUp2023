package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSub;

public class DriveCommands {
    
private DriveCommands() {

}
public static Command driveShift( DriveSub driveSub){
    return driveSub.runOnce(()-> driveSub.toggleShifters());
}
public static Command arcadeDrive(DriveSub driveSub, double move, double turn){
    return (driveSub.run(()-> driveSub.arcadeDrive(move, turn))).finallyDo((isInteruped)-> driveSub.arcadeDrive(0,0));
  }

}
