package frc.robot;

import java.util.Optional;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.auto.AutoCommands;

public class AutoModeSelector {
    enum DesiredMode {
        // Just example modes, add yours later
        DO_NOTHING,
        FULL_AUTO;
    }

    private final SendableChooser<DesiredMode> modeChooser;

    private final Optional<Command> autoMode = Optional.empty();

    private DesiredMode cachedAutoMode = DesiredMode.DO_NOTHING;

    public AutoModeSelector() {
        modeChooser = new SendableChooser<>();
        modeChooser.setDefaultOption("Do Nothing", DesiredMode.DO_NOTHING);
        modeChooser.addOption("Full Auto", DesiredMode.FULL_AUTO);
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
                return Optional.of(AutoCommands.fullAuto(RobotContainer.driveSub, RobotContainer.pigeonSub, RobotContainer.elevatorSub, RobotContainer.grabbySub, RobotContainer.dumpSub)); 
            default:
                System.out.println("No valid mode found. Doing nothing");
                return Optional.empty();
        }
    }

    public Optional<Command> getAutoMode() {
        return autoMode;
    } 

}
