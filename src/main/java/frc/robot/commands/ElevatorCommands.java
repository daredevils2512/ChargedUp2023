package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.logging.Logger;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.utils.Constants.ElevatorConstants;

public class ElevatorCommands {
    public static final Logger  logger = Logger.getLogger(ElevatorCommands.class.getSimpleName());

    private ElevatorCommands(){

    }

    public static Command runElevator(ElevatorSub elevatorSub, DoubleSupplier speed){
        return new RunCommand(() -> elevatorSub.setSpeed(speed.getAsDouble()), elevatorSub)
            .beforeStarting (() ->  logger.info("runElevator" + speed.getAsDouble()))
            .finallyDo((interrupted) -> elevatorSub.setSpeed(0));
    }

    public static Command runToLength(ElevatorSub elevatorSub, double length, double tolerance){
        BooleanSupplier elevatorUp = () -> elevatorSub.getLength() > length;
        DoubleSupplier speed = ()-> elevatorUp.getAsBoolean() ? ElevatorConstants.ELEVATOR_SPEED : -ElevatorConstants.ELEVATOR_SPEED;
        return runElevator(elevatorSub, speed).until(() -> Math.abs(elevatorSub.getLength() - length) <= tolerance);
    }    

    public static Command setElevatorExtended(ElevatorSub elevatorSub, boolean extended){
        return elevatorSub.runOnce(() -> elevatorSub.setExtended(extended))
            .beforeStarting(() ->  logger.info("setElevatorExtended" + extended));
    }

    public static Command elevatorToggle(ElevatorSub elevatorSub){
        return elevatorSub.runOnce(elevatorSub::toggleElevatorExtended);
    }
    public static Command setElevatorExtendedTrue(ElevatorSub elevatorSub){
        return elevatorSub.runOnce(()->elevatorSub.setExtended(true));
    }

    public static Command runToLengthPID(ElevatorSub elevatorSub, double length, double tolerance, double velocity){
        PIDController pid = new PIDController(ElevatorConstants.ELEVATOR_PID_KP, ElevatorConstants.ELEVATOR_PID_KI, ElevatorConstants.ELEVATOR_PID_KD);
        pid.setTolerance(tolerance, velocity);
        pid.close();
        return runElevator(elevatorSub, () -> pid.calculate(elevatorSub.getLength(), length))
            .beforeStarting(pid::reset)
            .until(pid::atSetpoint);
    }
}
