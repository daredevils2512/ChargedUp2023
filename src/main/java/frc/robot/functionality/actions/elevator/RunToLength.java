package frc.robot.functionality.actions.elevator;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import frc.robot.functionality.actions.Action;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.utils.Constants.ElevatorConstants;

public class RunToLength implements Action {
    private final ElevatorSub elevator;
    private final double length;
    private final double tolerance;

    private BooleanSupplier atHeight;
    private DoubleSupplier speed;

    public RunToLength(ElevatorSub elevator, double length, double tolerance) {
        this.elevator = elevator;
        this.length = length;
        this.tolerance = tolerance;
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
    public void onEnd() {
        elevator.setSpeed(0.0);
    }

    /** Returns true when the command should end. */
    @Override
    public boolean isFinished() {
        boolean finished = Math.abs(elevator.getLength() - length) <= tolerance;
        if (finished) {
            return true;
        }
        
        return true;
    }
    
}
