package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
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

    public static Command runToLength(ElevatorSub elevatorSub, double length, double tolerance){
        BooleanSupplier elevatorUp = () -> elevatorSub.getLength() < length;
        DoubleSupplier speed = ()-> elevatorUp.getAsBoolean() ? Constants.ELEVATOR_SPEED : -Constants.ELEVATOR_SPEED;
        return runElevator(elevatorSub, speed).until(() -> elevatorSub.getLength() - length <= tolerance);
    }    

    public static Command elevatorSet(ElevatorSub elevatorSub, boolean extended){
        return elevatorSub.runOnce(() -> elevatorSub.setElevatorExtended(extended));
    }

    public static Command elevatorToggle(ElevatorSub elevatorSub){
        return elevatorSub.runOnce(() -> elevatorSub.toggleElevatorExtended());
    }

    public static Command runToLengthPID(ElevatorSub elevatorSub, double length, double tolerance, double velocity){
        PIDController pid = new PIDController(Constants.ELEVATOR_PID_KP, Constants.ELEVATOR_PID_KI, Constants.ELEVATOR_PID_KD);
        pid.setTolerance(tolerance, velocity);
        return runElevator(elevatorSub, () -> pid.calculate(elevatorSub.getLength(), length))
            .beforeStarting(() -> pid.reset())
            .until(() -> pid.atSetpoint());
    }
}
