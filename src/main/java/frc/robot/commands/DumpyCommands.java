package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.lang.Math;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.DumpySub;
import frc.robot.utils.Constants;
import frc.robot.utils.Constants.DumpyConstants;

public class DumpyCommands {

    private DumpyCommands() {
        
    }

    public static Command rotateDumpy(DumpySub dumpySub, double speed) {
        return new RunCommand(() -> dumpySub.setDumpySpeed(speed), dumpySub)
        //until(dumpySub.reachedBounds())
        .finallyDo((interrupted) -> dumpySub.setDumpySpeed(0));
    }

    public static Command runBelt(DumpySub dumpySub, double speed) {
        return new RunCommand(() -> dumpySub.setBeltSpeed(speed), dumpySub)
        .finallyDo((interrupted) -> dumpySub.setBeltSpeed(0));
    }

    public static Command dumpyToAngle(DumpySub dumpySub, double angle) {
        BooleanSupplier dumpyAngle = () -> dumpySub.getAngle() < angle;
        DoubleSupplier speed = ()-> dumpyAngle.getAsBoolean() ? DumpyConstants.DUMPY_SPEED : -DumpyConstants.DUMPY_SPEED;
        return rotateDumpy(dumpySub, speed.getAsDouble()).until(() -> dumpyAngle.getAsBoolean() ? dumpySub.getAngle() > angle : dumpySub.getAngle() < angle);
    }

    public static Command dumpyToAnglePID(DumpySub dumpySub, double angle) {
        PIDController dumpyPID = new PIDController(DumpyConstants.DUMPY_KP,DumpyConstants.DUMPY_KI,DumpyConstants.DUMPY_KD);
        dumpyPID.setTolerance(DumpyConstants.DUMPY_TOLERANCE);
        return new PIDCommand(dumpyPID, () -> dumpySub.getAngle(), angle, (speed) -> rotateDumpy(dumpySub, speed)).until(() -> dumpyPID.atSetpoint());
    }

    public static Command dumpyToggle(DumpySub dumpySub) {
        BooleanSupplier defaultPosition = () -> Math.abs(dumpySub.getAngle()) < DumpyConstants.DUMPY_TOLERANCE;
        DoubleSupplier angle = () -> defaultPosition.getAsBoolean() ? DumpyConstants.DUMPY_UP : 0;
        return dumpyToAnglePID(dumpySub, angle.getAsDouble());
    }
    public static Command clawGrab(DumpySub dumpySub) {
        return dumpySub.runOnce(()-> dumpySub.toggleClaw());
    }


}
