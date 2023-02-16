package frc.robot.io;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.utils.Constants.IoConstants;

public class ButtonBox {
  private final Joystick joystick;

  public final Trigger topWhite;
  public final Trigger topRed;
  public final Trigger middleWhite;
  public final Trigger middleRed;
  public final Trigger bottomWhite;
  public final Trigger bottomRed;
  public final Trigger green;
  public final Trigger yellow;
  public final Trigger bigWhite;
  public final Trigger bigRed;

  public ButtonBox(int port) {
    joystick = new Joystick(port);

    topWhite = new JoystickButton(joystick, IoConstants.BUTTON_BOX_TOP_WHITE_PORT);
    bigWhite = new JoystickButton(joystick, IoConstants.BUTTON_BOX_BIG_WHITE_PORT);
    middleRed = new JoystickButton(joystick, IoConstants.BUTTON_BOX_MIDDLE_RED_PORT);
    bottomWhite = new JoystickButton(joystick, IoConstants.BUTTON_BOX_BOTTOM_WHITE_PORT);
    topRed = new JoystickButton(joystick, IoConstants.BUTTON_BOX_TOP_RED_PORT);
    green = new JoystickButton(joystick, IoConstants.BUTTON_BOX_GREEN_PORT);
    middleWhite = new JoystickButton(joystick, IoConstants.BUTTON_BOX_MIDDLE_WHITE_PORT);
    bigRed = new JoystickButton(joystick, IoConstants.BUTTON_BOX_BIG_RED_PORT);
    yellow = new JoystickButton(joystick, IoConstants.BUTTON_BOX_YELLOW_PORT);
    bottomRed = new JoystickButton(joystick, IoConstants.BUTTON_BOX_BOTTOM_RED_PORT);
  }

}
