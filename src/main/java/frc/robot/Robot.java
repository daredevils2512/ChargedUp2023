package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.functionality.CommandExecutor;
import frc.robot.functionality.actions.drive.ArcadeDrive;
import frc.robot.functionality.actions.drive.DriveShift;
import frc.robot.functionality.actions.dumpy.RotateDumpy;
import frc.robot.functionality.actions.elevator.ElevatorToggle;
import frc.robot.functionality.actions.elevator.RunElevator;
import frc.robot.functionality.actions.elevator.RunToLength;
import frc.robot.functionality.actions.grabby.GrabThingy;
import frc.robot.functionality.commands.Command;
import frc.robot.functionality.commands.FullAuto;
import frc.robot.io.Extreme;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.DumpySub;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.subsystems.GrabbySub;
import frc.robot.subsystems.PigeonSub;
import frc.robot.utils.Constants.ElevatorConstants;
import frc.robot.utils.Constants.IoConstants;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private CommandExecutor autoExecutor;

  private AutoModeSelector m_AutoModeSelector;

  private ElevatorSub m_ElevatorSub;
  private DriveSub driveSub;
  private DumpySub dumpSub;
  private PigeonSub pigeonSub;
  private GrabbySub grabbySub;

  private Extreme m_extreme;
  private CommandXboxController m_driverController;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_ElevatorSub = new ElevatorSub();
    driveSub = new DriveSub(); 
    dumpSub = new DumpySub();
    pigeonSub = new PigeonSub();
    grabbySub = new GrabbySub();
  
    m_extreme = new Extreme(1); // Move port to constats
    m_driverController = new CommandXboxController(IoConstants.XBOX_CONTROLLER_PORT);

    autoExecutor = new CommandExecutor(m_autonomousCommand);
    
    UsbCamera camera = CameraServer.startAutomaticCapture();
    camera.setResolution(640,480);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_AutoModeSelector.updateAutoChoice();
    m_autonomousCommand = m_AutoModeSelector.getAutoMode().get();

    // schedule the autonomous command (example)
    if (autoExecutor != null) {
      autoExecutor.execute();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autoExecutor != null) {
      autoExecutor.stop();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    if (m_driverController.rightBumper().getAsBoolean()) {
      new CommandExecutor(new Command() {
        @Override
        public void routine() {
          runAction(new DriveShift(driveSub));
        }
      });
    }

    new CommandExecutor(new Command() {
      @Override
      public void routine() {
        runAction(new ArcadeDrive(driveSub, () -> m_driverController.getLeftY(), () -> m_driverController.getRightX()));
      }
    });

    new CommandExecutor(new Command() {
      @Override
      public void routine() {
        runAction(new RotateDumpy(dumpSub, () -> m_extreme.getStickY()));
      };
    });

    if (m_extreme.joystickTopRight.getAsBoolean()) {
      new CommandExecutor(new Command() {
        @Override
        public void routine() {
          runAction(new RunElevator(m_ElevatorSub, () -> ElevatorConstants.ELEVATOR_SPEED));
        };
      });
    }

    if (m_extreme.joystickTopLeft.getAsBoolean()) {
      new CommandExecutor(new Command() {
        @Override
        public void routine() {
          runAction(new RunElevator(m_ElevatorSub, () -> -ElevatorConstants.ELEVATOR_SPEED));
        };
      });
    }

    if (m_extreme.trigger.getAsBoolean()) {
      new CommandExecutor(new Command() {
        @Override
        public void routine() {
          runAction(new GrabThingy(grabbySub));
        };
      });
    }

    if (m_extreme.sideButton.getAsBoolean()) {
      new CommandExecutor(new Command() {
        @Override
        public void routine() {
          runAction(new ElevatorToggle(m_ElevatorSub));
        };
      });
    }

    if (m_extreme.baseBackLeft.getAsBoolean()) {
      new CommandExecutor(new Command() {
        @Override
        public void routine() {
          runAction(new RunToLength(m_ElevatorSub, -4.8, 0.1));
        };
      });
    }

    if (m_extreme.baseBackRight.getAsBoolean()) {
      new CommandExecutor(new Command() {
        @Override
        public void routine() {
          runAction(new RunToLength(m_ElevatorSub, -2.3, 0.1));
        };
      });
    }

    if (m_extreme.baseMiddleLeft.getAsBoolean()) {
      new CommandExecutor(new Command() {
        @Override
        public void routine() {
          runAction(new RotateDumpy(dumpSub, () -> -0.5, 1));
        }
      });
    }

    if (m_extreme.baseFrontLeft.getAsBoolean()) {
      new CommandExecutor(new Command() {
        private Command fullAuto;
        @Override
        public void initialize() {
          fullAuto = new FullAuto(driveSub, pigeonSub, m_ElevatorSub, grabbySub, dumpSub);
        };
        @Override
        public void routine() {
          do {
            fullAuto.routine();
          } while (!fullAuto.isFinished());
        }
      });
    }


  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
