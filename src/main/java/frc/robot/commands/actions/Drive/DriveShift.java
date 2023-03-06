package frc.robot.commands.actions.Drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSub;

public class DriveShift extends CommandBase {
    private final DriveSub drivetrain;
    
    public DriveShift(DriveSub drivetrain) {
        this.drivetrain = drivetrain;

        addRequirements(this.drivetrain);
    }

    /** Called when the command starts */
    @Override
    public void initialize() { 
        
    }

    /** Called every time the scheduler runs while the command is scheduled. */
    @Override
    public void execute() {
        drivetrain.toggleShifters();
    }

    /** Called once the command ends or is interrupted. */
    @Override
    public void end(boolean interrupted) {
        
    }

    /** Returns true when the command should end. */
    @Override
    public boolean isFinished() {
        return true;
    }
    
}
