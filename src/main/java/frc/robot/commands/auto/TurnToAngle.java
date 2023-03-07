package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.utils.Constants.Auto;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.PigeonSub;

public class TurnToAngle extends CommandBase{
    private final DriveSub  DriveSub;
    private final PigeonSub  PigeonSub;
    private double yawTarget;
    private final int  angleToTurnTO;
    PIDController pid = new PIDController(Auto.AutoK_P, Auto.AutoK_I, Auto.AutoK_D);


    public TurnToAngle(DriveSub driveSub, PigeonSub pigeonSub, Integer angleToTurnTO) {
     this.DriveSub = driveSub;
     this.PigeonSub = pigeonSub; 
     this.angleToTurnTO = angleToTurnTO;

    addRequirements( DriveSub,  PigeonSub);
}
@Override
  public void initialize() {  
  System.out.println("Turning!!");
  yawTarget =  PigeonSub.getYaw() +  angleToTurnTO;
  }

  /** Called every time the scheduler runs while the command is scheduled. */
  @Override
  public void execute() {
   
    // double output = Constants.AutoK_P * (yawTarget -  PigeonSub.getYaw());
    double output = pid.calculate( PigeonSub.getYaw(), yawTarget);
     DriveSub.arcadeDrive(0, -output);
   
  }

  /** Called once the command ends or is interrupted. */
  @Override
  public void end(boolean interrupted) {
     DriveSub.arcadeDrive(0, 0);
    
  }

  /** Returns true when the command should end. */
  @Override
  public boolean isFinished() {
    return false;
  }
}