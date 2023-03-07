package frc.robot.functionality.commands;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.utils.Constants.Auto;
import frc.robot.functionality.actions.drive.ArcadeDrive;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.PigeonSub;

public class Stableize extends Command {
    private final DriveSub DriveSub;
    private final PigeonSub PigeonSub;
    PIDController pid = new PIDController(Auto.AutoK_P, Auto.AutoK_I, Auto.AutoK_D);

    public Stableize(DriveSub driveSub, PigeonSub pigeonSub) {
      DriveSub = driveSub;
      PigeonSub = pigeonSub; 
      pid.setTolerance(1);
    }

  /** Called every time the scheduler runs while the command is scheduled. */
  @Override
  public void routine() {
   // double output = Auto.AutoK_P * (0 - PigeonSub.getPitch());
    double output = pid.calculate(PigeonSub.getPitch(), 0);
    runAction(new ArcadeDrive(DriveSub, () -> output, () -> 0.0));

  }

  /** Called once the command ends or is interrupted. */
  @Override
  public void onEnd() {
    runAction(new ArcadeDrive(DriveSub, () -> 0.0, () -> 0.0));
  }

  /** Returns true when the command should end. */
  @Override
  public boolean isFinished() {
    return pid.atSetpoint();
  }
}