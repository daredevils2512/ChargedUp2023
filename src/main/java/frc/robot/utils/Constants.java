package frc.robot.utils;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

/** The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  private Constants() { }

  //elevator motors
  public static final int ELEVATOR_1ID = 1;
  public static final int ELEVATOR_2ID = 2;
  public static final double ELEVATOR_SPEED = 0.5;

  //elevator encoders
  public static final double ENCODER_PER_PULSE_DISTANCE = 123/123; //TODO not the right numbers
  public static final int ELEVATOR_ENCODER_CHANNEL1ID = 1;
  public static final int ELEVATOR_ENCODER_CHANNEL2ID = 2;


  //double solenoid
  public static final int FORWARD_CHANNEL = 0; //TODO channels might be wrong
  public static final int REVERSE_CHANNEL = 1; //TODO
  public static final DoubleSolenoid.Value EXTENDED = Value.kForward;
  public static final DoubleSolenoid.Value RETRACTED = Value.kReverse;
  
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
  
