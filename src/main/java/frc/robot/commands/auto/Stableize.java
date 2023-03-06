package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.utils.Constants.Auto;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.PigeonSub;

public class Stableize extends CommandBase{
    private final DriveSub m_DriveSub;
    private final PigeonSub m_PigeonSub;
    PIDController pid = new PIDController(Auto.AutoK_P, Auto.AutoK_I, Auto.AutoK_D);


    public Stableize(DriveSub driveSub, PigeonSub pigeonSub) {
    m_DriveSub = driveSub;
    m_PigeonSub = pigeonSub; 
   pid.setTolerance(1);
    addRequirements(m_DriveSub, m_PigeonSub);
}
@Override
  public void initialize() {  
  
  }

  /** Called every time the scheduler runs while the command is scheduled. */
  @Override
  public void execute() {
   // double output = Auto.AutoK_P * (0 - m_PigeonSub.getPitch());
    double output = pid.calculate(m_PigeonSub.getPitch(), 0);
    m_DriveSub.arcadeDrive(output, 0);
    
   
  }

  /** Called once the command ends or is interrupted. */
  @Override
  public void end(boolean interrupted) {
    m_DriveSub.arcadeDrive(0, 0);
    
  }

  /** Returns true when the command should end. */
  @Override
  public boolean isFinished() {
    return pid.atSetpoint();
  }
}