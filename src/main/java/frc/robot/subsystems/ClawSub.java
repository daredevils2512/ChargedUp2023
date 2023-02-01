package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;

public class ClawSub  extends SubsystemBase {
  private final DoubleSolenoid grabber;

  public ClawSub() {
    grabber = new DoubleSolenoid(Constants.PNEUMATICS_MODULE_TYPE, Constants.INTAKE_SHIFTER_FORWARD_ID1, Constants.INTAKE_SHIFTER_BACKWARD_ID1);
  }

  public void setExtended(boolean wantsExtended) {
    grabber.set(wantsExtended ? Constants.INTAKE_EXTENDED_VALUE : Constants.INTAKE_RETRACTED_VALUE);
  }

  public boolean getExtended() {
    return grabber.get() == Constants.INTAKE_EXTENDED_VALUE;
  }

  public void toggleExtended() {
    setExtended(!getExtended());
  }
}
