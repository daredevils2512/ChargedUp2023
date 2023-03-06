package frc.robot.commands.actions.Drive;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSub;

public class ArcadeDrive extends CommandBase {
    private final DriveSub drivetrain;
    private final DoubleSupplier move;
    private final DoubleSupplier turn;

    public ArcadeDrive(DriveSub drivetrain, DoubleSupplier move, DoubleSupplier turn) {
        this.drivetrain = drivetrain;
        this.move = move;
        this.turn = turn;

        addRequirements(this.drivetrain);
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
    public void end(boolean interrupted) {
        drivetrain.arcadeDrive(0.0, 0.0);
    }

    /** Returns true when the command should end. */
    @Override
    public boolean isFinished() {
        return false;
    }
    
}
