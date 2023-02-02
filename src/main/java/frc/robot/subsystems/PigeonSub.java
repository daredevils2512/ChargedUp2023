package frc.robot.subsystems;

import com.ctre.phoenix.sensors.WPI_Pigeon2;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;

public class PigeonSub extends SubsystemBase {
    private final WPI_Pigeon2 pigeon;

    private final NetworkTable table;
    private final NetworkTableEntry yawEntry;
    private final NetworkTableEntry pitchEntry;
    private final NetworkTableEntry rollEntry;

    public PigeonSub() {
        pigeon = new WPI_Pigeon2(Constants.PIGEON_PORT);
        
        table = NetworkTableInstance.create().getTable("Gyro");
        yawEntry = table.getEntry("Yaw");
        pitchEntry = table.getEntry("Pitch");
        rollEntry = table.getEntry("Roll");
    }
    
    public double[] getGravityVector() {
        double[] d = {0.0, 0.0, 0.0};
        pigeon.getGravityVector(d);
        return d;
    }

    public double[] getGyro() {
        double[] d = {0.0, 0.0, 0.0};
        pigeon.getRawGyro(d);
        return d;
    }

    public double getYaw() {
        return pigeon.getYaw();
    }

    public double getPitch() {
        return pigeon.getPitch();
    }

    public double getRoll() {
        return pigeon.getRoll();
    }

    @Override
    public void periodic() {
        yawEntry.setNumber(getYaw());
        pitchEntry.setNumber(getPitch());
        rollEntry.setNumber(getRoll());
    }

}