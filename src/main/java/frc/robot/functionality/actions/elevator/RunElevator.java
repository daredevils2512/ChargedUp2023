package frc.robot.functionality.actions.elevator;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.functionality.actions.Action;
import frc.robot.subsystems.ElevatorSub;

public class RunElevator implements Action {
    private final ElevatorSub elevator;
    private final DoubleSupplier speed;
    private final double timeout;
    private double startTime;

    public RunElevator(ElevatorSub elevator, DoubleSupplier speed, double timeout) {
        this.elevator = elevator;
        this.speed = speed;
        this.timeout = timeout;
    }

    public RunElevator(ElevatorSub elevator, DoubleSupplier speed) {
        this(elevator, speed, -1.0);
    }

    /** Called when the command starts */
    @Override
    public void initialize() { 
        startTime = Timer.getFPGATimestamp();
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
        if (timeout == -1) return true;
        double elapsed = Timer.getFPGATimestamp() - startTime;
        return elapsed >= timeout;
    }
    
}
