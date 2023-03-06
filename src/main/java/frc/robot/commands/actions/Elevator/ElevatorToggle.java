package frc.robot.commands.actions.Elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSub;

public class ElevatorToggle extends CommandBase {
    private final ElevatorSub elevator;

    public ElevatorToggle(ElevatorSub elevator) {
        this.elevator = elevator;

        addRequirements(this.elevator);
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
    public void end(boolean interrupted) {
        
    }

    /** Returns true when the command should end. */
    @Override
    public boolean isFinished() {
        return true;
    }
    
}
