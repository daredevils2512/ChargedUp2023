package frc.robot.functionality.actions.elevator;

import frc.robot.functionality.actions.Action;
import frc.robot.subsystems.ElevatorSub;

public class ElevatorToggle implements Action {
    private final ElevatorSub elevator;

    public ElevatorToggle(ElevatorSub elevator) {
        this.elevator = elevator;
    }

    /** Called when the command starts */
    @Override
    public void initialize() { 
        
    }

    /** Called every time the scheduler runs while the command is scheduled. */
    @Override
    public void execute() {
        elevator.toggleElevatorExtended();
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
