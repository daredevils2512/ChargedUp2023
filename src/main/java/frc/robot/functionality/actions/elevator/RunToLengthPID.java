package frc.robot.functionality.actions.elevator;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.functionality.actions.Action;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.utils.Constants.ElevatorConstants;

public class RunToLengthPID implements Action {
    private final ElevatorSub elevator;
    private final double length;
    private final double tolerance;
    private final double velocity;

    private PIDController pid;

    public RunToLengthPID(ElevatorSub elevator, double length, double tolerance, double velocity) {
        this.elevator = elevator;
        this.length = length;
        this.tolerance = tolerance;
        this.velocity = velocity;
    }

    /** Called when the command starts */
    @Override
    public void initialize() {
        pid = new PIDController(ElevatorConstants.ELEVATOR_PID_KP, ElevatorConstants.ELEVATOR_PID_KI, ElevatorConstants.ELEVATOR_PID_KD);
        pid.setTolerance(tolerance, velocity);
        pid.close();
    }

    /** Called every time the scheduler runs while the command is scheduled. */
    @Override
    public void execute() {
        elevator.setSpeed(pid.calculate(elevator.getLength(), length));
    }

    /** Called once the command ends or is interrupted. */
    @Override
    public void onEnd() {
        elevator.setSpeed(0.0);
    }

    /** Returns true when the command should end. */
    @Override
    public boolean isFinished() {
        return true;
    }
    
}
