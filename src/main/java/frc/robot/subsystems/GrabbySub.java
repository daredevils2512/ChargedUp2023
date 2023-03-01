package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants.DumpyConstants;
import frc.robot.utils.Constants.GrabbyConstants;

public class GrabbySub  extends SubsystemBase {
  private final DoubleSolenoid grabber;
  private final NetworkTable grabbyNetworkTable = NetworkTableInstance.getDefault().getTable(getName());
  private final NetworkTableEntry grabbyExtended = grabbyNetworkTable.getEntry("extended");
 private final WPI_TalonSRX dumpyMotor;
  

  public GrabbySub() {
    grabber = new DoubleSolenoid(GrabbyConstants.PNEUMATICS_MODULE_TYPE, GrabbyConstants.FORWARD_CHANNEL, GrabbyConstants.REVERSE_CHANNEL);
    grabbyExtended.setBoolean(getGrab());
    dumpyMotor = new WPI_TalonSRX(DumpyConstants.dumpyID);
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
