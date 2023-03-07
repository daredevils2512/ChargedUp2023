package frc.robot.functionality.actions.elevator;

import frc.robot.functionality.actions.Action;
import frc.robot.subsystems.ElevatorSub;

public class ElevatorExtended implements Action {
    private ElevatorSub elevator;
    private boolean extended;

    public ElevatorExtended(ElevatorSub elevator, boolean extended) {
        this.elevator = elevator;
        this.extended = extended;
    }

    /** Called when the command starts */
    @Override
    public void initialize() { 
        
    }

    /** Called every time the scheduler runs while the command is scheduled. */
    @Override
    public void execute() {
        elevator.setExtended(extended);
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
