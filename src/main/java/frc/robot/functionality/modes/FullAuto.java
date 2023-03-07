package frc.robot.functionality.modes;

import frc.robot.functionality.actions.elevator.ElevatorToggle;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.DumpySub;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.subsystems.GrabbySub;
import frc.robot.subsystems.PigeonSub;

// TODO: Rename this to say what it actually does
public class FullAuto extends Command {
    private final DriveSub drivetrain;
    private final PigeonSub pigeon;
    private final ElevatorSub elevator;
    private final GrabbySub grabby;
    private final DumpySub dumpy;
    private Command runToLengthAndDrop;
    private Command chargeStation;

    public FullAuto(DriveSub drivetrain, PigeonSub pigeon, ElevatorSub elevator, GrabbySub grabby, DumpySub dumpy) {
        this.drivetrain = drivetrain;
        this.pigeon = pigeon;
        this.elevator = elevator;
        this.grabby = grabby;
        this.dumpy = dumpy;
    }

    public void initialize() {
        runToLengthAndDrop = new RunToLengthAndDrop(elevator, dumpy, grabby, -4.8, 0.1);
        chargeStation = new ChargeStation(pigeon, drivetrain);
    }

    public void routine() {
        runAction(new ElevatorToggle(elevator));

        do {
            runToLengthAndDrop.routine();
        } while (!runToLengthAndDrop.isFinished());

        runAction(new ElevatorToggle(elevator));

        do {
            chargeStation.routine();
        } while (!chargeStation.isFinished());
    }
    
}
