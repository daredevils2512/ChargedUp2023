package frc.robot;

import frc.robot.commands.ElevatorCommands;
import frc.robot.io.Extreme;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.utils.Constants.ElevatorConstants;
import frc.robot.utils.Constants.IoConstants;

import frc.robot.commands.DumpyCommands;
import frc.robot.io.Extreme;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.DumpySub;
import frc.robot.utils.Constants;
import frc.robot.utils.Constants.DumpyConstants;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  
  private final ElevatorSub m_ElevatorSub = new ElevatorSub();

  private final Extreme m_Extreme = new Extreme(0); // Move port to constats

  DriveSub driveSub = new DriveSub(); 
  DumpySub dumpySub = new DumpySub();

  // Replace with CommandPS4Controller or CommandJoystick if needed

  private final CommandXboxController m_driverController =
  new CommandXboxController(IoConstants.XBOX_CONTROLLER_PORT);

  private final Extreme m_extreme = new Extreme(1);


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
    dumpySub.setDefaultCommand(DumpyCommands.rotateDumpy(dumpySub, m_extreme.getStickY()));
    m_extreme.joystickUp.whileTrue(DumpyCommands.runBelt(dumpySub, DumpyConstants.beltSpeed));
    m_extreme.joystickDown.whileTrue(DumpyCommands.runBelt(dumpySub, -DumpyConstants.beltSpeed));

  }  


  /**
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
   return null; 
  }
}
