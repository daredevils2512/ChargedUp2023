package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants.GrabbyConstants;

public class GrabbySub  extends SubsystemBase {
  private final DoubleSolenoid grabber;
  private final NetworkTable grabbyNetworkTable = NetworkTableInstance.getDefault().getTable(getName());
  private final NetworkTableEntry grabbyExtended = grabbyNetworkTable.getEntry("extended");
  
  

  public GrabbySub() {
    grabber = new DoubleSolenoid(GrabbyConstants.PNEUMATICS_MODULE_TYPE, GrabbyConstants.FORWARD_CHANNEL, GrabbyConstants.REVERSE_CHANNEL);
    grabbyExtended.setBoolean(getGrab());
  }

  public void setGrab(boolean wantsGrab) {
    grabber.set(wantsGrab ? GrabbyConstants.INTAKE_EXTENDED_VALUE : GrabbyConstants.INTAKE_RETRACTED_VALUE);
    grabbyExtended.setBoolean(wantsGrab);
  }

  public boolean getGrab() {
    return grabber.get() == GrabbyConstants.INTAKE_EXTENDED_VALUE;
  }

  public void toggleGrab() {
    setGrab(!getGrab());
  }
}
