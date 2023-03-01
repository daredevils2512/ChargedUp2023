package frc.robot;

import frc.robot.commands.DriveCommands;
// import frc.robot.commands.DumpyCommands;
import frc.robot.io.Extreme;
import frc.robot.subsystems.DriveSub;
// import frc.robot.subsystems.DumpySub;
// import frc.robot.utils.Constants.DumpyConstants;
import frc.robot.subsystems.DumpySub;
import java.util.function.DoubleSupplier;
import com.ctre.phoenix.sensors.Pigeon2;
import frc.robot.commands.ElevatorCommands;
import frc.robot.commands.GrabbyCommands;
import frc.robot.commands.auto.AutoCommands;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.subsystems.GrabbySub;
import frc.robot.subsystems.PigeonSub;
import frc.robot.utils.Constants.ElevatorConstants;
import frc.robot.utils.Constants.IoConstants;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  
  private final ElevatorSub m_ElevatorSub = new ElevatorSub();
  private final DriveSub driveSub = new DriveSub(); 
  private final DumpySub dumpSub = new DumpySub();
  private final PigeonSub pigeonSub = new PigeonSub();
  private final GrabbySub grabbySub = new GrabbySub();

  private final Extreme m_extreme = new Extreme(1); // Move port to constats
  private final CommandXboxController m_driverController = new CommandXboxController(IoConstants.XBOX_CONTROLLER_PORT);

  /** The container for the ro
   * bot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    UsbCamera camera = CameraServer.startAutomaticCapture();
    camera.setResolution(640,480);
  }
// beware the watermelon man
// how bad can the watermelon man possibly be?
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
    driveSub.setDefaultCommand(driveSub.run(() -> driveSub.arcadeDrive( -m_driverController.getLeftY(),m_driverController.getRightX())));
    dumpSub.setDefaultCommand(dumpSub.run(()-> dumpSub.setDumpySpeed(m_extreme.getStickY())));
    m_driverController.rightBumper().onTrue(DriveCommands.driveShift(driveSub));  
    m_extreme.joystickTopRight.whileTrue(ElevatorCommands.runElevator(m_ElevatorSub, ()-> ElevatorConstants.ELEVATOR_SPEED));
    m_extreme.joystickTopLeft.whileTrue(ElevatorCommands.runElevator(m_ElevatorSub, ()-> -ElevatorConstants.ELEVATOR_SPEED));
    m_extreme.trigger.onTrue(GrabbyCommands.grabThingy(grabbySub));
    m_extreme.sideButton.onTrue(ElevatorCommands.elevatorToggle(m_ElevatorSub));
    m_extreme.baseBackLeft.whileTrue(ElevatorCommands.runToLength(m_ElevatorSub, -4.8, .1));
    m_extreme.baseBackRight.whileTrue(ElevatorCommands.runToLength(m_ElevatorSub, -2.3, .1));
    m_extreme.baseFrontLeft.whileTrue(AutoCommands.fullAuto(driveSub, pigeonSub, m_ElevatorSub, grabbySub));
    //add run to length commands, -4.8, 
    //2.3
 
  }
   
  /**
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
   return AutoCommands.fullAuto(driveSub, pigeonSub, m_ElevatorSub, grabbySub); 
  }
}
