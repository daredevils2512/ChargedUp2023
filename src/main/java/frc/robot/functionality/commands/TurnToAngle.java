package frc.robot.functionality.commands;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.utils.Constants.Auto;
import frc.robot.functionality.actions.drive.ArcadeDrive;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.PigeonSub;

public class TurnToAngle extends Command {
    private final DriveSub m_DriveSub;
    private final PigeonSub m_PigeonSub;
    private double yawTarget;
    private final int m_angleToTurnTO;
    PIDController pid = new PIDController(Auto.AutoK_P, Auto.AutoK_I, Auto.AutoK_D);


    public TurnToAngle(DriveSub driveSub, PigeonSub pigeonSub, Integer angleToTurnTO) {
      m_DriveSub = driveSub;
      m_PigeonSub = pigeonSub; 
      m_angleToTurnTO = angleToTurnTO;
      
      System.out.println("Turning!!");
      yawTarget = m_PigeonSub.getYaw() + m_angleToTurnTO;
    }

  /** Called every time the scheduler runs while the command is scheduled. */
  @Override
  public void routine() {
   
    // double output = Constants.AutoK_P * (yawTarget - m_PigeonSub.getYaw());
    double output = pid.calculate(m_PigeonSub.getYaw(), yawTarget);
    runAction(new ArcadeDrive(m_DriveSub, () -> 0.0, () -> -output));
   
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