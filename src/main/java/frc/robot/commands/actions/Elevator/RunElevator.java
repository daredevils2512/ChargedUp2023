package frc.robot.commands.actions.Elevator;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSub;

public class RunElevator extends CommandBase {
    private final ElevatorSub elevator;
    private final DoubleSupplier speed;

    public RunElevator(ElevatorSub elevator, DoubleSupplier speed) {
        this.elevator = elevator;
        this.speed = speed;

        addRequirements(this.elevator);
    }

    /** Called when the command starts */
    @Override
    public void initialize() { 

    }

    /** Called every time the scheduler runs while the command is scheduled. */
    @Override
    public void execute() {
        elevator.setSpeed(speed.getAsDouble());
    }

    /** Called once the command ends or is interrupted. */
    @Override
    public void end(boolean interrupted) {
        elevator.setSpeed(0.0);
    }

    /** Returns true when the command should end. */
    @Override
    public boolean isFinished() {
        return false;
    }
    
}
