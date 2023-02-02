package frc.robot;

import frc.robot.io.XboxController;
import frc.robot.subsystems.DriveSub;
import frc.robot.utils.Constants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  DriveSub driveSub = new DriveSub(); 
  XboxController m_driverController = new XboxController(Constants.IoConstants.XBOX_CONTROLLER_PORT);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    driveSub.setDefaultCommand(driveSub.run(() -> driveSub.arcadeDrive(m_driverController.getYAxisLeft(), m_driverController.getXAxisRight())));
  }

  /**
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
   return null; 
  }
}
