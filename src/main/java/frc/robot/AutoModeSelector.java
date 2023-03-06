package frc.robot;

import java.util.Optional;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class AutoModeSelector {
    enum DesiredMode {
        // Just example modes, add yours later
        DONOTHING,
        MODE1,
        MODE2;
    }

    private final SendableChooser<DesiredMode> modeChooser;

    private final Optional<Command> autoMode = Optional.empty();

    private DesiredMode cachedAutoMode = DesiredMode.DONOTHING;

    public AutoModeSelector() {
        modeChooser = new SendableChooser<>();
        modeChooser.setDefaultOption("Do Nothing", DesiredMode.DONOTHING);
        modeChooser.addOption("Mode 1", DesiredMode.MODE1);
        modeChooser.addOption("Mode 2", DesiredMode.MODE2);
        SmartDashboard.putData(modeChooser);
    }

    public void updateAutoChoice() {
        DesiredMode desiredMode = modeChooser.getSelected();

        if (desiredMode == null) {
            desiredMode = DesiredMode.DONOTHING;
        }

        if (cachedAutoMode != desiredMode) {
            getAutoMode(desiredMode);
        }

        cachedAutoMode = desiredMode;
    }

    private Optional<Command> getAutoMode(DesiredMode desiredMode) {
        switch (desiredMode) {
            case DONOTHING:
                return Optional.empty();
            case MODE1:
                return Optional.empty(); // too lazy to make entire commands for this. just return a class. you get the point
            case MODE2:
                return Optional.empty(); // too lazy to make entire commands for this. just return a class. you get the point
            default:
                System.out.println("No valid mode found. Doing nothing");
                return Optional.empty();
        }
    }

    public Optional<Command> getAutoMode() {
        return autoMode;
    } 

}
