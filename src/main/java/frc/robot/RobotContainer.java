package frc.robot;

import frc.robot.commands.DriveCommands;
import frc.robot.io.Extreme;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.DumpySub;
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
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  
  private final ElevatorSub elevatorSub = new ElevatorSub();
  private final DriveSub driveSub = new DriveSub(); 
  private final DumpySub dumpSub = new DumpySub();
  private final PigeonSub pigeonSub = new PigeonSub();
  private final GrabbySub grabbySub = new GrabbySub();

  private final Extreme extreeme = new Extreme(1); // Move port to constats
  private final CommandXboxController driverController = new CommandXboxController(IoConstants.XBOX_CONTROLLER_PORT);

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
    dumpSub.setDefaultCommand(dumpSub.run(()-> dumpSub.setDumpySpeed(extreeme.getStickY())));
    
    driverController.rightBumper().onTrue(DriveCommands.driveShift(driveSub));  
    driverController.rightTrigger().onTrue(GrabbyCommands.grabThingy(grabbySub));
    driverController.leftTrigger().whileTrue(GrabbyCommands.limitGrab(grabbySub));

    extreeme.joystickTopRight.whileTrue(ElevatorCommands.runElevator(elevatorSub, ()-> ElevatorConstants.ELEVATOR_SPEED));
    extreeme.joystickTopLeft.whileTrue(ElevatorCommands.runElevator(elevatorSub, ()-> -ElevatorConstants.ELEVATOR_SPEED));
    extreeme.trigger.onTrue(GrabbyCommands.grabThingy(grabbySub));
    extreeme.sideButton.whileTrue(GrabbyCommands.limitGrab(grabbySub));
    extreeme.baseMiddleLeft.onTrue(ElevatorCommands.elevatorToggle(elevatorSub));
    extreeme.baseBackLeft.onTrue(ElevatorCommands.runToLength(elevatorSub, -4.8, .1));
    extreeme.baseBackRight.onTrue(ElevatorCommands.runToLength(elevatorSub, -2.3, .1));
    extreeme.baseFrontLeft.onTrue(AutoCommands.fullAuto(driveSub, pigeonSub, elevatorSub, grabbySub, dumpSub));
    extreeme.joystickBottomLeft.onTrue(ElevatorCommands.setElevatorExtendedTrue(elevatorSub));
    
  }
   
  /**
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
   return AutoCommands.fullAuto(driveSub, pigeonSub, elevatorSub, grabbySub, dumpSub);
  }
}
