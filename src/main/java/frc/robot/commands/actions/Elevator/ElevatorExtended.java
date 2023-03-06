package frc.robot.commands.actions.Elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSub;

public class ElevatorExtended extends CommandBase {
    private ElevatorSub elevator;
    private boolean extended;

    public ElevatorExtended(ElevatorSub elevator, boolean extended) {
        this.elevator = elevator;
        this.extended = extended;

        addRequirements(this.elevator);
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
    public void end(boolean interrupted) {
        
    }

    /** Returns true when the command should end. */
    @Override
    public boolean isFinished() {
        return true;
    }
    
}
