package frc.robot.functionality.modes;

import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.PigeonSub;

public class OrientateAndDrive extends Command {

  private final PigeonSub gyro;
  private final DriveSub driveSub;
  private double startYaw;

  public OrientateAndDrive(DriveSub drivetrain, PigeonSub gyro) {
    driveSub = drivetrain;
    this.gyro = gyro;
  }

  @Override
  public void routine() {
    // turn left -> yaw+
    // if (Auto.AUTO_DESIRED_YAW > startYaw) {
    //   runAction(new ArcadeDrive(driveSub, () -> 0.0, () -> 1.0));
    // }

  }

}
