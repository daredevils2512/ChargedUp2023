package frc.robot.commands.actions.Elevator;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.utils.Constants.ElevatorConstants;

public class RunToLength extends CommandBase {
    private final ElevatorSub elevator;
    private final double length;
    private final double tolerance;

    private BooleanSupplier atHeight;
    private DoubleSupplier speed;

    public RunToLength(ElevatorSub elevator, double length, double tolerance) {
        this.elevator = elevator;
        this.length = length;
        this.tolerance = tolerance;

        addRequirements(this.elevator);
    }

    /** Called when the command starts */
    @Override
    public void initialize() { 
        atHeight = () -> this.elevator.getLength() > length;
        speed = () -> atHeight.getAsBoolean() ? ElevatorConstants.ELEVATOR_SPEED : -ElevatorConstants.ELEVATOR_SPEED;
        
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
        boolean finished = Math.abs(elevator.getLength() - length) <= tolerance;
        if (finished) {
            return true;
        }
        
        return false;
    }
    
}
