package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ClawSub;

public final class ClawCommands{
private ClawCommands(){}


public static Command grabThingy(ClawSub clawSub) {
        return new InstantCommand(clawSub::toggleExtended, clawSub);
    }
} 



