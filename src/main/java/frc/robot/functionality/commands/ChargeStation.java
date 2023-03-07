package frc.robot.functionality.commands;

import frc.robot.functionality.actions.drive.ArcadeDrive;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.PigeonSub;

public class ChargeStation extends Command {
    private final PigeonSub pigeon;
    private final DriveSub drivetrain;
    private Command stableize;

    public ChargeStation(PigeonSub pigeon, DriveSub drivetrain) {
        this.pigeon = pigeon;
        this.drivetrain = drivetrain;
    }

    @Override
    public void initialize() {
        stableize = new Stableize(drivetrain, pigeon);
    }

    @Override
    public void routine() {
        do {
            runAction(new ArcadeDrive(drivetrain, () -> -0.25, () -> 0.0));
        } while (pigeon.getPitch() <= 8);

        do {
            stableize.routine();
        } while (stableize.isFinished());
    }

    @Override
    public boolean isFinished() {
        return stableize.isFinished();
    }

    @Override
    public void onEnd() {
        runAction(new ArcadeDrive(drivetrain, () -> 0.0, () -> 0.0));
    }
    
}
