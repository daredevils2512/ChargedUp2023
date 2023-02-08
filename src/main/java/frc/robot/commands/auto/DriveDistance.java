package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSub;
import frc.robot.utils.Constants.Auto;

public class DriveDistance extends CommandBase{
    private final DriveSub m_driveSub;
    private final double m_driveTarget;
    double kP = 1;

    public DriveDistance (DriveSub driveSub, Integer driveTarget) {
        m_driveSub = driveSub;
        m_driveTarget = driveTarget;

        addRequirements(m_driveSub);
    }

    public void initialize() {
        m_driveSub.setLeftDistancePerPulse(0/0);
        m_driveSub.setRightDistancePerPulse(0/0);
    }

    public void execute() {
        if (m_driveSub.getLeftDistance() < m_driveTarget) {
            m_driveSub.arcadeDrive(Auto.AUTO_DRIVE_SPEED, 0);
        } else {
            m_driveSub.arcadeDrive(0, 0);
        }
    }
}
