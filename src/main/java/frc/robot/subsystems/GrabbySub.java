package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.GrabbyCommands;
import frc.robot.utils.Constants.DumpyConstants;
import frc.robot.utils.Constants.GrabbyConstants;

public class GrabbySub  extends SubsystemBase {
  private final DoubleSolenoid grabber;
  private final DigitalInput m_limitSwitch;
  private final NetworkTable grabbyNetworkTable = NetworkTableInstance.getDefault().getTable(getName());
  private final NetworkTableEntry grabbyExtended = grabbyNetworkTable.getEntry("extended");
  private final NetworkTableEntry grabbyLimit = grabbyNetworkTable.getEntry("Tripped");
  private final NetworkTableEntry getGrabby = grabbyNetworkTable.getEntry("getGrabby");
  

  public GrabbySub() {
    m_limitSwitch = new DigitalInput(GrabbyConstants.LIMIT_SWITCH_PORT);
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

  public void limitGrab(){
    if (m_limitSwitch.get()== true && getGrab() == true) {
      setGrab(!getGrab());
    }

  } 
  @Override
  public void periodic() {
    grabbyLimit.setBoolean(m_limitSwitch.get());
    getGrabby.setBoolean(getGrab());
  }
}