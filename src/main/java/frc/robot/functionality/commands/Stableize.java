package frc.robot.functionality.commands;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.utils.Constants.Auto;
import frc.robot.functionality.actions.drive.ArcadeDrive;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.PigeonSub;

public class Stableize extends Command {
    private final DriveSub m_DriveSub;
    private final PigeonSub m_PigeonSub;
    PIDController pid = new PIDController(Auto.AutoK_P, Auto.AutoK_I, Auto.AutoK_D);

    public Stableize(DriveSub driveSub, PigeonSub pigeonSub) {
      m_DriveSub = driveSub;
      m_PigeonSub = pigeonSub; 
      pid.setTolerance(1);
    }

  /** Called every time the scheduler runs while the command is scheduled. */
  @Override
  public void routine() {
   // double output = Auto.AutoK_P * (0 - m_PigeonSub.getPitch());
    double output = pid.calculate(m_PigeonSub.getPitch(), 0);
    runAction(new ArcadeDrive(m_DriveSub, () -> output, () -> 0.0));

  }

  /** Called once the command ends or is interrupted. */
  @Override
  public void onEnd() {
    runAction(new ArcadeDrive(m_DriveSub, () -> 0.0, () -> 0.0));
  }

  /** Returns true when the command should end. */
  @Override
  public boolean isFinished() {
    return pid.atSetpoint();
  }
}