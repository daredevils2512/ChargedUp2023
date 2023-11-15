package frc.robot;

import frc.robot.commands.DriveCommands;
import frc.robot.commands.ElevatorCommands;
import frc.robot.commands.GrabbyCommands;
import frc.robot.commands.auto.AutoCommands;
import frc.robot.io.Extreme;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.DumpySub;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.subsystems.GrabbySub;
import frc.robot.subsystems.PigeonSub;
import frc.robot.utils.Constants.ElevatorConstants;
import frc.robot.utils.Constants.IoConstants;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  
  public static final ElevatorSub elevatorSub = new ElevatorSub();
  public static final DriveSub driveSub = new DriveSub(); 
  public static final DumpySub dumpSub = new DumpySub();
  public static final PigeonSub pigeonSub = new PigeonSub();
  public static final GrabbySub grabbySub = new GrabbySub();

  public static final Extreme extreme = new Extreme(1); // Move port to constats
  public static final CommandXboxController driverController = new CommandXboxController(IoConstants.XBOX_CONTROLLER_PORT);

  public static final AutoModeSelector autoSelector = new AutoModeSelector();

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
    driveSub.setDefaultCommand(driveSub.run(() -> driveSub.arcadeDrive( -driverController.getLeftY(),driverController.getRightX())));
    dumpSub.setDefaultCommand(dumpSub.run(()-> dumpSub.setDumpySpeed(extreme.getStickY())));
    
    driverController.rightBumper().onTrue(DriveCommands.driveShift(driveSub));  
    driverController.rightTrigger().onTrue(GrabbyCommands.grabThingy(grabbySub));
    driverController.leftTrigger().whileTrue(GrabbyCommands.limitGrab(grabbySub));

    extreme.joystickTopRight.whileTrue(ElevatorCommands.runElevator(elevatorSub, ()-> ElevatorConstants.ELEVATOR_SPEED));
    extreme.joystickTopLeft.whileTrue(ElevatorCommands.runElevator(elevatorSub, ()-> -ElevatorConstants.ELEVATOR_SPEED));
    extreme.trigger.onTrue(GrabbyCommands.grabThingy(grabbySub));
    extreme.sideButton.whileTrue(GrabbyCommands.limitGrab(grabbySub));
    extreme.baseMiddleLeft.onTrue(ElevatorCommands.elevatorToggle(elevatorSub));
    extreme.baseBackLeft.onTrue(ElevatorCommands.runToLength(elevatorSub, -4.8, .1));
    extreme.baseBackRight.onTrue(ElevatorCommands.runToLength(elevatorSub, -2.3, .1));
    extreme.baseFrontLeft.onTrue(AutoCommands.fullAuto(driveSub, pigeonSub, elevatorSub, grabbySub, dumpSub));
    extreme.joystickBottomLeft.onTrue(ElevatorCommands.setElevatorExtendedTrue(elevatorSub));
    
  }

  public Command getAutoMode() {
    autoSelector.updateAutoChoice();
    return autoSelector.getAutoMode().get();
  }

}
