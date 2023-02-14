package frc.robot;


import frc.robot.commands.ElevatorCommands;

import frc.robot.io.Extreme;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.utils.Constants.ElevatorConstants;
import frc.robot.utils.Constants.IoConstants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  
  private final ElevatorSub m_ElevatorSub = new ElevatorSub();

  private final Extreme m_Extreme = new Extreme(0); // Move port to constats

  // Replace with CommandPS4Controller or CommandJoystick if needed
  // private final CommandXboxController m_driverController =
  //     new CommandXboxController(OperatorConstants.kDriverControllerPort);
  DriveSub driveSub = new DriveSub(); 

  // Replace with CommandPS4Controller or CommandJoystick if needed
   private final CommandXboxController m_driverController =
  new CommandXboxController(IoConstants.XBOX_CONTROLLER_PORT);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }
// beware the watermelon man
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
    m_Extreme.joystickTopRight.whileTrue(ElevatorCommands.runElevator(m_ElevatorSub, ()-> ElevatorConstants.ELEVATOR_SPEED));
    driveSub.setDefaultCommand(driveSub.run(() -> driveSub.arcadeDrive(m_driverController.getLeftX(), m_driverController.getRightY())));
  }
   

  /**
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
   return null; 
  }
}
