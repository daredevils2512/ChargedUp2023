package frc.robot.io;

import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.utils.Constants;

public class XboxController {
  // Joystick
  private final edu.wpi.first.wpilibj.XboxController controller;

  // Buttons 
  public final Trigger aButton;
  public final Trigger bButton;
  public final Trigger xButton;
  public final Trigger yButton;
  public final Trigger leftBumper;
  public final Trigger rightBumper;
  public final Trigger backArrow;
  public final Trigger startArrow;
  public final Trigger leftStickButton;
  public final Trigger rightStickButton;
  public final POVButton dPadUp;
  public final POVButton dPadUpRight;
  public final POVButton dPadRight;
  public final POVButton dPadDownRight;
  public final POVButton dPadDown;
  public final POVButton dPadDownLeft;
  public final POVButton dPadLeft;
  public final POVButton dPadUpLeft;
  
  public XboxController(int port) {
    // Joystick 
    controller = new edu.wpi.first.wpilibj.XboxController(port);

    // Buttons
    aButton = new Trigger(controller::getAButton);
    bButton = new Trigger(controller::getBButton);
    xButton = new Trigger(controller::getXButton);
    yButton = new Trigger(controller::getYButton);
    leftBumper = new Trigger(controller::getLeftBumper);
    rightBumper = new Trigger(controller::getRightBumper);
    backArrow = new Trigger(controller::getBackButton);
    startArrow = new Trigger(controller::getStartButton);
    leftStickButton = new Trigger(controller::getLeftStickButton);
    rightStickButton = new Trigger(controller::getRightStickButton);
    dPadUp = new POVButton(controller, Constants.XBOX_POV_UP_DEGREES);
    dPadUpRight = new POVButton(controller, Constants.XBOX_POV_UP_RIGHT_DEGREES);
    dPadRight = new POVButton(controller, Constants.XBOX_POV_RIGHT_DEGREES);
    dPadDownRight = new POVButton(controller, Constants.XBOX_POV_DOWN_RIGHT_DEGREES);
    dPadDown = new POVButton(controller, Constants.XBOX_POV_DOWN_DEGREES);
    dPadDownLeft = new POVButton(controller, Constants.XBOX_POV_DOWN_LEFT_DEGREES);
    dPadLeft = new POVButton(controller, Constants.XBOX_POV_LEFT_DEGREES);
    dPadUpLeft = new POVButton(controller, Constants.XBOX_POV_UP_LEFT_DEGREES);
  
  }

  /** @return XAxisLeft */
  public double getXAxisLeft() {
    return controller.getLeftX();
  }

  /** @return YAxisLeft */
  public double getYAxisLeft() {
    return controller.getLeftY();
  }

  /** @return XAxisRight */
  public double getXAxisRight() {
    return controller.getRightX();
  }

  /** @return YAxisRight */
  public double getYAxisRight() {
    return controller.getRightY();
  }

  /** @return Left Trigger */
  public double getLeftTrigger() {
    return controller.getLeftTriggerAxis();
  }

  /** @return Right Trigger */
  public double getRightTrigger() {
    return controller.getRightTriggerAxis();
  }

  /** Sets the left rumble
   * @param amount The amount to rumble between 0 (inclusive) and 1 (inclusive)
   */
  public void setLeftRumble(double amount) {
    controller.setRumble(Constants.XBOX_LEFT_RUMBLE, amount);
  }

  /** Sets the right rumble
   * @param amount The amount to rumble between 0 (inclusive) and 1 (inclusive)
   */
  public void setRightRumble(double amount) {
    controller.setRumble(Constants.XBOX_RIGHT_RUMBLE, amount);
  }

  /** Sets the right and left rumble
   * @param amount The amount to rumble between 0 (inclusive) and 1 (inclusive)
   */
  public void setRumble(double amount) {
    setLeftRumble(amount);
    setRightRumble(amount);
  }

  /** Stops left rumble */
  public void stopLeftRumble() {
    controller.setRumble(Constants.XBOX_LEFT_RUMBLE, 0.0);
  }

  /** Stops right rumble */
  public void stopRightRumble() {
    controller.setRumble(Constants.XBOX_RIGHT_RUMBLE, 0.0);
  }

  /** Stops rumble */
  public void stopRumble() {
    stopLeftRumble();
    stopRightRumble();
  }

  /** @return True if the DPad is being pressed on the top. */
  public boolean getDPadTop() {
    return dPadUp.getAsBoolean();
  }

  /** @return True if the DPad is being pressed on the top right. */
  public boolean getDPadTopRight() {
    return dPadUpRight.getAsBoolean();
  }

  /** @return True if the DPad is being pressed on the right. */
  public boolean getDPadRight() {
    return dPadRight.getAsBoolean();
  }

  /** @return True if the DPad is being pressed on the bottom right. */
  public boolean getDPadBottomRight() {
    return dPadDownRight.getAsBoolean();
  }

  /** @return True if the DPad is being pressed on the bottom. */
  public boolean getDPadBottom() {
    return dPadDown.getAsBoolean();
  }

  /** @return True if the DPad is being pressed on the bottom left. */
  public boolean getDPadBottomLeft() {
    return dPadDownLeft.getAsBoolean();
  }

  /** @return True if the DPad is being pressed on the left. */
  public boolean getDPadLeft() {
    return dPadLeft.getAsBoolean();
  }

  /** @return True if the DPad is being pressed on the top left. */
  public boolean getDPadTopLeft() {
    return dPadUpLeft.getAsBoolean();
  }

  /** @return True if the DPad is not being pressed. */
  public boolean getDPadReleased() {
    return getDPad() == Constants.XBOX_POV_RELEASED_DEGREES;
  }

  /** @return The angle of the DPad in degrees.
   * The angle is represented in 8 degrees (right being 90, left being 270).
   * If none are being pressed, -1 is returned.
   */
  public double getDPad() {
    return controller.getPOV();
  }

}