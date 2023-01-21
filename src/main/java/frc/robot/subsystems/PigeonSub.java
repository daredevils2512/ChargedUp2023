package frc.robot.subsystems;

import com.ctre.phoenix.sensors.WPI_Pigeon2;
import frc.robot.utils.Constants;

public class PigeonSub {
    private final WPI_Pigeon2 pigeon;

    public PigeonSub() {
        pigeon = new WPI_Pigeon2(Constants.PIGEON_PORT);
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

}