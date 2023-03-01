package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants.DumpyConstants;

public class DumpySub extends SubsystemBase{

    private final WPI_TalonSRX dumpyMotor;
    private final WPI_TalonSRX beltMotor;
   // private final DigitalInput topSwitch;
   // private final DigitalInput bottomSwitch;
    private final double slowRate;

    private final NetworkTable dumpyNetworkTable = NetworkTableInstance.getDefault().getTable(getName());
    private final NetworkTableEntry dumpyEncoderUnits = dumpyNetworkTable.getEntry("dumpy encoder units: ");  
    private final NetworkTableEntry dumpyAngle = dumpyNetworkTable.getEntry("dumpy angle: ");
    private final NetworkTableEntry dumpySpeed = dumpyNetworkTable.getEntry("dumpy speed: ");
    


    public DumpySub() {
        //Constructor
        dumpyMotor = new WPI_TalonSRX(DumpyConstants.dumpyID);
        beltMotor = new WPI_TalonSRX(DumpyConstants.dumpyBeltID);
        slowRate = DumpyConstants.dumpySpeed;
        dumpyMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
    }

    public void setDumpySpeed(double speed) {
        double outputSpeed = speed * slowRate;
        dumpyMotor.set(ControlMode.PercentOutput, outputSpeed);
        dumpySpeed.setDouble(speed);
      }
    

    public double getAngle() {
        return dumpyMotor.getSelectedSensorPosition() / DumpyConstants.ENCODER_RESOLUTION * DumpyConstants.DEGREES_PER_ROTATION;
    }

    @Override
    public void periodic() {
        dumpyEncoderUnits.setDouble(dumpyMotor.getSelectedSensorPosition());
        dumpyAngle.setDouble(getAngle());
     }
    

}
