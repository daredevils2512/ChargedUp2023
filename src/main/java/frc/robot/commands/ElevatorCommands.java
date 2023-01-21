package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.physical.ElevatorSub;
import frc.robot.utils.Constants;

public class ElevatorCommands {

    private ElevatorCommands(){

    }

    public static Command runElevator(ElevatorSub elevatorSub, DoubleSupplier speed){
        return new RunCommand(() -> elevatorSub.setSpeed(speed.getAsDouble()), elevatorSub)
            .finallyDo((interrupted) -> elevatorSub.setSpeed(0));
    }

    public static Command runToLength(ElevatorSub elevatorSub, Double length, double tolerance){
        BooleanSupplier elevatorUp = () -> elevatorSub.elevatorLength() < length;
        DoubleSupplier speed = ()-> elevatorUp.getAsBoolean() ? Constants.ELEVATOR_SPEED : -Constants.ELEVATOR_SPEED;
        return runElevator(elevatorSub, speed).until(() -> elevatorSub.elevatorLength() - length <= tolerance);
    }    

    public static Command elevatorSet(ElevatorSub elevatorSub, boolean extended){
        return elevatorSub.runOnce(()-> elevatorSub.setElevatorExtended(extended));
    }

    public static Command elevatorToggle(ElevatorSub elevatorSub){
        return elevatorSub.runOnce(()-> elevatorSub.toggleElevatorExtended());
    }
}
