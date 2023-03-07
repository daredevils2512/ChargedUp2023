package frc.robot.functionality.modes;

import frc.robot.functionality.actions.dumpy.RotateDumpy;
import frc.robot.functionality.actions.elevator.RunToLength;
import frc.robot.functionality.actions.grabby.GrabThingy;
import frc.robot.subsystems.DumpySub;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.subsystems.GrabbySub;

public class RunToLengthAndDrop extends Command {
    private final ElevatorSub elevator;
    private final DumpySub dumpy;
    private final GrabbySub grabby;
    private final double length;
    private final double tolerance;

    public RunToLengthAndDrop(ElevatorSub elevator, DumpySub dumpy, GrabbySub grabby, double length, double tolerance) {
        this.elevator = elevator;
        this.dumpy = dumpy;
        this.grabby = grabby;
        this.length = length;
        this.tolerance = tolerance;
    }

    @Override
    public void routine() {
        runAction(new RunToLength(elevator, length, tolerance));
        runAction(new RotateDumpy(dumpy, () -> -0.5, 1.0));
        runAction(new GrabThingy(grabby));
        runAction(new RotateDumpy(dumpy, () -> -0.5, 0.75));
        runAction(new RunToLength(elevator, -0.5, 1.0));
    }
    
}
