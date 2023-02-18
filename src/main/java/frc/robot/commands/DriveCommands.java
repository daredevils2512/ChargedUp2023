package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSub;

public class DriveCommands {
    
private DriveCommands() {

}

public static Command arcadeDrive(DriveSub driveSub, DoubleSupplier move, DoubleSupplier turn){
    return driveSub.run(() -> driveSub.arcadeDrive(move.getAsDouble(), turn.getAsDouble()));
}

}
