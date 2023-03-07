package frc.robot.functionality.commands;

import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.PigeonSub;

public class OrientateAndDrive extends Command {

  private final PigeonSub m_gyro;
  private final DriveSub m_driveSub;
  private double startYaw;

  public OrientateAndDrive(DriveSub drivetrain, PigeonSub gyro) {
    m_driveSub = drivetrain;
    m_gyro = gyro;
  }

  @Override
  public void routine() {
    // turn left -> yaw+
    // if (Auto.AUTO_DESIRED_YAW > startYaw) {
    //   runAction(new ArcadeDrive(m_driveSub, () -> 0.0, () -> 1.0));
    // }

  }

}
