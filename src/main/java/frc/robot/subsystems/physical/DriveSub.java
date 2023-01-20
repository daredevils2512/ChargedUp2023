
package frc.robot.subsystems.physical;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

//Imports go here :) 

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class DriveSub implements Subsystem {
    //Public finals :)
    private final WPI_TalonFX frontLeft;
    private final MotorControllerGroup left;
    public DriveSub() {
        //Construct them bad bois

        //Motors here, name them something good like frontLeft, ect ect. 
        frontLeft = new WPI_TalonFX(0); //TODO: put values into a constants folder. 
        left = new MotorControllerGroup(frontLeft, null);
        


    }
    
}
