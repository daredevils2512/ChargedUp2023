package frc.robot.commands.automodes;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSub;
import frc.robot.utils.Constants.Auto;

public class DriveDistance extends CommandBase {
  private final DriveSub m_driveSub;
  private final double m_driveDistance;
  private double m_driveTarget;
  private final PIDController m_pid = new PIDController(Auto.DRIVETRAIN_KP, Auto.DRIVETRAIN_KI, Auto.DRIVETRAIN_KD);

  public DriveDistance(DriveSub driveSub, double driveDistance, double tolerance, double velocity) {
    m_driveSub = driveSub;
    m_driveDistance = driveDistance;
    m_pid.setTolerance(tolerance, velocity);

    addRequirements(m_driveSub);
  }

  @Override
  public void initialize() {
  //   m_pid.reset();
  //   m_driveTarget = (m_driveSub.getLeftDistance() + m_driveDistance);
  // }

  // @Override
  // public void execute() {
  //   double output = m_pid.calculate(m_driveSub.getLeftDistance(), m_driveTarget);
  //   m_driveSub.arcadeDrive(output, 0); 
  }

  @Override
  public boolean isFinished() {
    return m_pid.atSetpoint();
  }

  @Override
  public void end(boolean interrupted) {
      m_driveSub.arcadeDrive(0, 0);
  }
}

