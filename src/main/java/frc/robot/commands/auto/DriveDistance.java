package frc.robot.commands.auto;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSub;
import frc.robot.utils.Constants.Auto;

public class DriveDistance extends CommandBase {
  private final DriveSub  driveSub;
  private final double  driveDistance;
  private double  driveTarget;
  private final PIDController  pid = new PIDController(Auto.DRIVETRAIN_KP, Auto.DRIVETRAIN_KI, Auto.DRIVETRAIN_KD);

  public DriveDistance(DriveSub driveSub, double driveDistance, double tolerance, double velocity) {
     this.driveSub = driveSub;
     this.driveDistance = driveDistance;
     pid.setTolerance(tolerance, velocity);

    addRequirements( driveSub);
  }

  @Override
  public void initialize() {
  //    pid.reset();
  //    driveTarget = ( driveSub.getLeftDistance() + driveDistance);
  // }

  // @Override
  // public void execute() {
  //   double output =  pid.calculate( driveSub.getLeftDistance(),  driveTarget);
  //    driveSub.arcadeDrive(output, 0); 
  }

  @Override
  public boolean isFinished() {
    return  pid.atSetpoint();
  }

  @Override
  public void end(boolean interrupted) {
       driveSub.arcadeDrive(0, 0);
  }
}

