package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.GrabbySub;

public final class GrabbyCommands{
    private GrabbyCommands(){}
    public static Command limitGrab(GrabbySub grabbySub) {
        return new RunCommand(()-> grabbySub.limitGrab(), grabbySub);
    }
    public static Command grabThingy(GrabbySub grabbySub) {
        return new InstantCommand(grabbySub::toggleGrab, grabbySub);
    }
} 



