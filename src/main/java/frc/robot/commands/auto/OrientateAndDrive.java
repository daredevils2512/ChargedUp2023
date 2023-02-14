package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.PigeonSub;

import frc.robot.utils.Constants;
import frc.robot.utils.Constants.Auto;

public class OrientateAndDrive extends CommandBase {
  
    private final PigeonSub m_gyro;
    private final DriveSub m_driveSub;
    private double startYaw;

    public OrientateAndDrive(DriveSub drivetrain, PigeonSub gyro) {
       
        m_driveSub = drivetrain;
        m_gyro = gyro;
        addRequirements(drivetrain, gyro);
    }

    @Override
    public void initialize() {
        startYaw = m_gyro.getYaw();
    }

    @Override
    public void execute() {
        // turn left -> yaw+
        if (Auto.AUTO_DESIRED_YAW > startYaw) {
            m_driveSub.arcadeDrive(0.0, 1.0);
        }

    }

    @Override
    public void end(boolean interrupted) {

    }

}
