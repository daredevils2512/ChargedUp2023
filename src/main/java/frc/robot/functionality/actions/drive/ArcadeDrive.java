package frc.robot.functionality.actions.drive;

import java.util.function.DoubleSupplier;

import frc.robot.functionality.actions.Action;
import frc.robot.subsystems.DriveSub;

public class ArcadeDrive implements Action {
    private final DriveSub drivetrain;
    private final DoubleSupplier move;
    private final DoubleSupplier turn;

    public ArcadeDrive(DriveSub drivetrain, DoubleSupplier move, DoubleSupplier turn) {
        this.drivetrain = drivetrain;
        this.move = move;
        this.turn = turn;
    }

    /** Called when the command starts */
    @Override
    public void initialize() { 
        
    }

    /** Called every time the scheduler runs while the command is scheduled. */
    @Override
    public void execute() {
        drivetrain.arcadeDrive(move.getAsDouble(), turn.getAsDouble());
    }

    /** Called once the command ends or is interrupted. */
    @Override
    public void onEnd() {
        drivetrain.arcadeDrive(0.0, 0.0);
    }

    /** Returns true when the command should end. */
    @Override
    public boolean isFinished() {
        return true;
    }
    
}
