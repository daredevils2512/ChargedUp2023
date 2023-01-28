package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;

public class DumpSub extends SubsystemBase{

    private final WPI_VictorSPX dumpMotor;
    private final double slowRate;

    public DumpSub() {
        //Constructor
        dumpMotor = new WPI_VictorSPX(Constants.dumpID);
        slowRate = Constants.dumpSpeedID;

    }

    public void setClimbSpeed(double speed) {
        double outputSpeed = speed * slowRate;
        dumpMotor.set(ControlMode.PercentOutput, outputSpeed);
      }
    
}
