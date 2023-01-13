
package frc.robot.subsystems.physical;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;

//Imports go here :) 

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class DriveSub implements Subsystem {
    //Public finals :)
    private final WPI_TalonFX frontLeft; 
    private final WPI_TalonFX frontRight;
    private final WPI_TalonFX backLeft;
    private final WPI_TalonFX backRight;
    private final MotorControllerGroup left;
    private final MotorControllerGroup right; 
    public DriveSub() {
        //Construct them bad bois

        //Motors here, name them something good like frontLeft, ect ect. 
        frontLeft = new WPI_TalonFX(0); //TODO: put values into a constants folder. 
        frontRight = new WPI_TalonFX(0);
        backLeft = new WPI_TalonFX(0);
        backRight = new WPI_TalonFX(0);

        left = new MotorControllerGroup(frontLeft, backLeft);
        right = new MotorControllerGroup(frontRight, backRight);

    }
    void arcadeDrive(double move, double turn) {
        left.set(move + turn);
        right.set(move - turn); 
        // TODO
    }
   public Command arcadeDriveCommand(DoubleSupplier move, DoubleSupplier turn) {
        return run(() -> {
            arcadeDrive(move.getAsDouble(), turn.getAsDouble());
        });
    }
}
