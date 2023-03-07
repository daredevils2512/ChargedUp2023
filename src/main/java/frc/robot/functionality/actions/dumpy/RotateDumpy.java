package frc.robot.functionality.actions.dumpy;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.functionality.actions.Action;
import frc.robot.subsystems.DumpySub;

public class RotateDumpy implements Action {
    private final DumpySub dumpy;
    private final DoubleSupplier speed;
    private final double timeout;
    private double startTime;

    public RotateDumpy(DumpySub dumpy, DoubleSupplier speed, double timeout) {
        this.dumpy = dumpy;
        this.speed = speed;
        this.timeout = timeout;
    }

    public RotateDumpy(DumpySub dumpy, DoubleSupplier speed) {
        this(dumpy, speed, -1.0);
    }

    /** Called when the command starts */
    @Override
    public void initialize() { 
        startTime = Timer.getFPGATimestamp();
    }

    /** Called every time the scheduler runs while the command is scheduled. */
    @Override
    public void execute() {
        dumpy.setDumpySpeed(speed.getAsDouble());
    }

    /** Called once the command ends or is interrupted. */
    @Override
    public void onEnd() {
        dumpy.setDumpySpeed(0.0);
    }

    /** Returns true when the command should end. */
    @Override
    public boolean isFinished() {
        if (timeout == -1) return true;
        double elapsed = Timer.getFPGATimestamp() - startTime;
        return elapsed >= timeout;
    }
    
}
