// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final PneumaticsModuleType PNEUMATICS_MODULE_TYPE = PneumaticsModuleType.CTREPCM;
  public static final int INTAKE_SHIFTER_FORWARD_ID1 = 4;
  public static final int INTAKE_SHIFTER_BACKWARD_ID1 = 5;
  public static final Value INTAKE_EXTENDED_VALUE = Value.kForward;
  public static final Value INTAKE_RETRACTED_VALUE = Value.kReverse;
  public static class DrivetrainConstants {
    public static final int DRIVE_LEFT_1_ID = 1;
    //drive front left 
    public static final int DRIVE_LEFT_2_ID = 2;
    //drive back left
    public static final int DRIVE_RIGHT_1_ID = 3;
    // drive right front
    public static final int DRIVE_RIGHT_2_ID = 4;
    //drive back right
    public static final int SHIFTER_FORWARD_CHANNEL = 0;
    public static final int SHIFTER_REVERSE_CHANNEL = 1; 
    public static final DoubleSolenoid.Value HIGH_GEAR_VALUE = DoubleSolenoid.Value.kForward;
    public static final DoubleSolenoid.Value LOW_GEAR_VALUE = DoubleSolenoid.Value.kReverse;
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

}
