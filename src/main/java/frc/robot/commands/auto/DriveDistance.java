package frc.robot.commands.auto;

import org.ejml.dense.row.MatrixFeatures_CDRM;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSub;
import frc.robot.utils.Constants;
import frc.robot.utils.Constants.Auto;

public class DriveDistance extends CommandBase {
  private final DriveSub m_driveSub;
  private final double m_driveTarget;
  private final PIDController m_pid = new PIDController(Auto.DRIVETRAIN_KP, Auto.DRIVETRAIN_KI, Auto.DRIVETRAIN_KD);

  public DriveDistance(DriveSub driveSub, Integer driveTarget) {
    m_driveSub = driveSub;
    m_driveTarget = driveTarget;

    addRequirements(m_driveSub);
  }

  @Override
  public void initialize() {
    m_pid.reset();
  }

  @Override
  public void execute() {
    double distance = m_pid.calculate(m_driveSub.getLeftDistance(), m_driveTarget);
    m_driveSub.arcadeDrive(distance, 0);
  }

  @Override
  public boolean isFinished() {
    return m_driveSub.getLeftDistance() >= m_driveTarget;
  }

  @Override
  public void end(boolean interrupted) {
      m_driveSub.arcadeDrive(0, 0);
  }
}

