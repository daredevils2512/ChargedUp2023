package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;

public class DumpSub extends SubsystemBase{

    private final WPI_VictorSPX dumpyMotor;
    private final WPI_VictorSPX beltMotor;
    private final double slowRate;

    public DumpSub() {
        //Constructor
        dumpyMotor = new WPI_VictorSPX(Constants.dumpyID);
        beltMotor = new WPI_VictorSPX(Constants.dumpyBeltID);
        slowRate = Constants.dumpSpeed;

        dumpyMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
    }

    public void setDumpySpeed(double speed) {
        double outputSpeed = speed * slowRate;
        dumpyMotor.set(ControlMode.PercentOutput, outputSpeed);
      }
    
    public void setBeltSpeed(double speed) {
        beltMotor.set(ControlMode.PercentOutput, speed);
    }

    public double getAngle() {
        return dumpyMotor.getSelectedSensorPosition() / Constants.ENCODER_RESOLUTION * Constants.DEGREES_PER_ROTATION;
    }

 
    

}
