package frc.robot;

import java.util.Optional;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.functionality.modes.ChargeStation;
import frc.robot.functionality.modes.Command;
import frc.robot.functionality.modes.FullAuto;
import frc.robot.functionality.modes.OrientateAndDrive;
import frc.robot.functionality.modes.RunToLengthAndDrop;

public class AutoModeSelector {
    enum DesiredMode {
        // Just example modes, add yours later
        DO_NOTHING,
        FULL_AUTO,
        ORIENTATE_AND_DRIVE,
        RUN_TO_LENGTH_AND_DROP,
        CHARGE_STATION;
    }

    private final SendableChooser<DesiredMode> modeChooser;

    private final Optional<Command> autoMode = Optional.empty();

    private DesiredMode cachedAutoMode = DesiredMode.DO_NOTHING;

    public AutoModeSelector() {
        modeChooser = new SendableChooser<>();
        modeChooser.setDefaultOption("Do Nothing", DesiredMode.DO_NOTHING);
        modeChooser.addOption("Full Auto", DesiredMode.FULL_AUTO);
        modeChooser.addOption("Orientate and Drive", DesiredMode.ORIENTATE_AND_DRIVE);
        modeChooser.addOption("Run to Length and Drop", DesiredMode.RUN_TO_LENGTH_AND_DROP);
        modeChooser.addOption("Charge Station", DesiredMode.CHARGE_STATION);
        SmartDashboard.putData(modeChooser);
    }

    public void updateAutoChoice() {
        DesiredMode desiredMode = modeChooser.getSelected();

        if (desiredMode == null) {
            desiredMode = DesiredMode.DO_NOTHING;
        }

        if (cachedAutoMode != desiredMode) {
            getAutoMode(desiredMode);
        }

        cachedAutoMode = desiredMode;
    }

    private Optional<Command> getAutoMode(DesiredMode desiredMode) {
        switch (desiredMode) {
            case DO_NOTHING:
                return Optional.empty();
            case FULL_AUTO:
                return Optional.of(new FullAuto()); 
            case ORIENTATE_AND_DRIVE:
                return Optional.of(new OrientateAndDrive()); 
            case RUN_TO_LENGTH_AND_DROP:
                return Optional.of(new RunToLengthAndDrop());
            case CHARGE_STATION:
                return Optional.of(new ChargeStation());
            default:
                System.out.println("No valid mode found. Doing nothing");
                return Optional.empty();
        }
    }

    public Optional<Command> getAutoMode() {
        return autoMode;
    } 

}
