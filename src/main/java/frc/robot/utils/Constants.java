package frc.robot.utils;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

public final class Constants {

  public static class Auto{ 
    public static final double AUTO_DESIRED_YAW = 100;
    public static final int PIGEON_PORT = 1;
    public static final double AUTO_DRIVE_SPEED = 0.5;
    //PID for Gyro
    public static final double AutoK_P = 0.015;
    public static final double AutoK_I = 0.00;
    public static final double AutoK_D = 0.000;
    //PID for drivetrain
    public static final double DRIVETRAIN_KP = 0;
    public static final double DRIVETRAIN_KI = 0;
    public static final double DRIVETRAIN_KD = 0;
  }

  public static class DrivetrainConstants {

    //Shifters
    public static final PneumaticsModuleType PNEUMATICS_MODULE_TYPE = PneumaticsModuleType.CTREPCM;
    public static final int SHIFTER_FORWARD_CHANNEL = 1;
    public static final int SHIFTER_REVERSE_CHANNEL = 6;
    public static final DoubleSolenoid.Value HIGH_GEAR_VALUE = DoubleSolenoid.Value.kForward;
    public static final DoubleSolenoid.Value LOW_GEAR_VALUE = DoubleSolenoid.Value.kReverse;

    //Motors
    public static final double DRIVETRAIN_SPEED = 1;
    public static final int DRIVE_FRONT_LEFT_ID = 1; 
    public static final int DRIVE_BACK_LEFT_ID = 2;
    public static final int DRIVE_FRONT_RIGHT_ID = 3;
    public static final int DRIVE_BACK_RIGHT_ID = 4;

    //Encoders
    public static final int DRIVETRAIN_LEFT_ENCODER_A = 0;
    public static final int DRIVETRAIN_LEFT_ENCODER_B = 1;
    public static final int DRIVETRAIN_RIGHT_ENCODER_A = 2;
    public static final int DRIVETRAIN_RIGHT_ENCODER_B = 3;
    public static final double PULSES_PER_ROTATIONS = 256;
    public static final double WHEEL_CIRCUMFERENCE = 6* Math.PI;
    public static final double DISTANCE_PER_PULSE = WHEEL_CIRCUMFERENCE/PULSES_PER_ROTATIONS;
    //shifters 1,6
  }

  public static class ElevatorConstants {

    // elevator motors
    public static final int ELEVATOR_1ID = 5;
    public static final int ELEVATOR_2ID = 6;
    public static final double ELEVATOR_SPEED = .9;

    // elevator encoders
    public static final double ENCODER_PER_PULSE_DISTANCE = 1; // TODO fix numbers
    public static final int ELEVATOR_ENCODER_CHANNEL1ID = 1;
    public static final int ELEVATOR_ENCODER_CHANNEL2ID = 2;

    public static final double MAX_ELEVATOR_LENGTH = -5;

    public static final int TICKS_PER_REVOLUTION = 4096;
    public static final int DISTANCE_PER_REVOLUTION = 1; // TODO fix numbers
    public static final double GEAR_RATIO = 1; // TODO fix numbersg

    public static final int ELEVATOR_LIMIT_SWITCH_CHANNEL = 4; // 

    // double solenoid
    public static final int FORWARD_CHANNEL = 5; // TODO channels might be wrong
    public static final int REVERSE_CHANNEL = 2; // TODO
    public static final DoubleSolenoid.Value EXTENDED = Value.kForward;
    public static final DoubleSolenoid.Value RETRACTED = Value.kReverse;

    // elevator pid
    public static final double ELEVATOR_PID_KP = 0; // TODO change all numbers
    public static final double ELEVATOR_PID_KI = 0; // TODO
    public static final double ELEVATOR_PID_KD = 0; // TODO
  }
  
  public static class DumpyConstants {
    //IDs
    public static final int dumpyID = 7;
    public static final int dumpyBeltID = 8;
    public static final int BOTTOM_SWITCH_CHANNEL = 0;
    public static final int TOP_SWITCH_CHANNEL = 1;

    //Encoder values
    public static final double ENCODER_RESOLUTION = 2048;
    public static final double GEAR_RATIO = .0001;
    public static final double DEGREES_PER_ROTATION = 360 * GEAR_RATIO; 

    //Speed modifiers for manual inputs
    public static final double dumpySpeed = 1;
    public static final double beltSpeed = 1;

    //Automatic dumpy movement values 
    public static final double DUMPY_SPEED = 0.5;
    public static final double DUMPY_TOLERANCE = 0.5;
    public static final double DUMPY_UP = 45;  

    //PID for Dumpy
    public static final double DUMPY_KP = 1;
    public static final double DUMPY_KI = 0;
    public static final double DUMPY_KD = 0;

    //Auto belt values
    public static final double AUTO_BELT = 0.5;
    public static final double BELT_TIMER = 1.5;

  }

  public static class GrabbyConstants {
    public static final PneumaticsModuleType PNEUMATICS_MODULE_TYPE = PneumaticsModuleType.CTREPCM;
    public static final int FORWARD_CHANNEL = 0; // TODO channels might be wrong
    public static final int REVERSE_CHANNEL = 7; //TODO
    public static final Value INTAKE_EXTENDED_VALUE = Value.kForward;
    public static final Value INTAKE_RETRACTED_VALUE = Value.kReverse;
  }

  public static class IoConstants {
    // Control Board
    public static final int XBOX_CONTROLLER_PORT = 0;
    public static final int EXTREME_PORT = 1;
    public static final int BUTTON_BOX_PORT = 2;

    // Xbox Controller
    public static final int XBOX_POV_UP_DEGREES = 0;
    public static final int XBOX_POV_UP_RIGHT_DEGREES = 45;
    public static final int XBOX_POV_RIGHT_DEGREES = 90;
    public static final int XBOX_POV_DOWN_RIGHT_DEGREES = 135;
    public static final int XBOX_POV_DOWN_DEGREES = 180;
    public static final int XBOX_POV_DOWN_LEFT_DEGREES = 225;
    public static final int XBOX_POV_LEFT_DEGREES = 270;
    public static final int XBOX_POV_UP_LEFT_DEGREES = 315;
    public static final int XBOX_POV_RELEASED_DEGREES = -1;
    public static final RumbleType XBOX_LEFT_RUMBLE = RumbleType.kLeftRumble;
    public static final RumbleType XBOX_RIGHT_RUMBLE = RumbleType.kRightRumble;

    // Extreme
    public static final int EXTREME_TRIGGER_PORT = 1;
    public static final int EXTREME_SIDE_BUTTON_PORT = 2;
    public static final int EXTREME_JOYSTICK_BOTTOM_LEFT_PORT = 3;
    public static final int EXTREME_JOYSTICK_BOTTOM_RIGHT_PORT = 4;
    public static final int EXTREME_JOYSTICK_TOP_LEFT_PORT = 5;
    public static final int EXTREME_JOYSTICK_TOP_RIGHT_PORT = 6;
    public static final int EXTREME_BASE_FRONT_LEFT_PORT = 7;
    public static final int EXTREME_BASE_FRONT_RIGHT_PORT = 8;
    public static final int EXTREME_BASE_MIDDLE_LEFT_PORT = 9;
    public static final int EXTREME_BASE_MIDDLE_RIGHT_PORT = 10;
    public static final int EXTREME_BASE_BACK_LEFT_PORT = 11;
    public static final int EXTREME_BASE_BACK_RIGHT_PORT = 12;
    public static final int EXTREME_STICK_X_AXIS_ID = 0;
    public static final int EXTREME_STICK_Y_AXIS_ID = 1;
    public static final int EXTREME_STICK_Z_AXIS_ID = 2;
    public static final int EXTREME_SLIDER_AXIS_ID = 3;
    public static final int EXTREME_POV_UP_DEGREES = 0;
    public static final int EXTREME_POV_UP_RIGHT_DEGREES = 45;
    public static final int EXTREME_POV_RIGHT_DEGREES = 90;
    public static final int EXTREME_POV_DOWN_RIGHT_DEGREES = 135;
    public static final int EXTREME_POV_DOWN_DEGREES = 180;
    public static final int EXTREME_POV_DOWN_LEFT_DEGREES = 225;
    public static final int EXTREME_POV_LEFT_DEGREES = 270;
    public static final int EXTREME_POV_UP_LEFT_DEGREES = 315;

    // Button Box
    public static final int BUTTON_BOX_TOP_WHITE_PORT = 2;
    public static final int BUTTON_BOX_BIG_WHITE_PORT = 3;
    public static final int BUTTON_BOX_MIDDLE_RED_PORT = 4;
    public static final int BUTTON_BOX_BOTTOM_WHITE_PORT = 5;
    public static final int BUTTON_BOX_TOP_RED_PORT = 6;
    public static final int BUTTON_BOX_GREEN_PORT = 7;
    public static final int BUTTON_BOX_MIDDLE_WHITE_PORT = 8;
    public static final int BUTTON_BOX_BIG_RED_PORT = 14;
    public static final int BUTTON_BOX_YELLOW_PORT = 15;
    public static final int BUTTON_BOX_BOTTOM_RED_PORT = 16;

  }

  private Constants() {
  }
}
