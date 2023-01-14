package frc.robot.subsystems;

import com.ctre.phoenix.sensors.WPI_PigeonIMU;
import frc.robot.utils.Constants;

public class PigeonSub {
    private final WPI_PigeonIMU pigeon;

    public PigeonSub() {
        pigeon = new WPI_PigeonIMU(Constants.PIGEON_PORT);
    }

    public double[] getGyro() {
        double[] d = {getYaw(), getPitch(), getRoll()};
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