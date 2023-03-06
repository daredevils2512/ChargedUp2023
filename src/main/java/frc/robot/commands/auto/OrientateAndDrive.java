package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.PigeonSub;
import frc.robot.utils.Constants.Auto;

public class OrientateAndDrive extends CommandBase {

  private final PigeonSub  gyro;
  private final DriveSub  driveSub;
  private double startYaw;

  public OrientateAndDrive(DriveSub drivetrain, PigeonSub gyro) {

     driveSub = drivetrain;
     this.gyro = gyro;
    addRequirements(drivetrain, gyro);
  }

  @Override
  public void initialize() {
    startYaw =  gyro.getYaw();
  }

  @Override
  public void execute() {
    // turn left -> yaw+
    if (Auto.AUTO_DESIRED_YAW > startYaw) {
       driveSub.arcadeDrive(0.0, 1.0);
    }

  }

  @Override
  public void end(boolean interrupted) {

  }

}
