package frc.robot.functionality.actions.drive;

import frc.robot.functionality.actions.Action;
import frc.robot.subsystems.DriveSub;

public class DriveShift implements Action {
    private final DriveSub drivetrain;
    
    public DriveShift(DriveSub drivetrain) {
        this.drivetrain = drivetrain;
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
    public void onEnd() {
        
    }

    /** Returns true when the command should end. */
    @Override
    public boolean isFinished() {
        return true;
    }
    
}
