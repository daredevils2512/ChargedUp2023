package frc.robot.subsystems;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants.ElevatorConstants;

public class ElevatorSub extends SubsystemBase {
    private final TalonSRX m_rightMotor;
    private final TalonSRX m_leftMotor;
    private final DigitalInput m_limitSwitch;
    private final DoubleSolenoid m_doubleSolenoid;
    private final PIDController m_pid = new PIDController(0, 0, 0);
    private boolean m_lastUsedPID = false;
    private final Logger m_logger = Logger.getLogger(getName());
    private final NetworkTable m_networkTable = NetworkTableInstance.getDefault().getTable(getName());
    private final NetworkTableEntry m_setSpeedEntry = m_networkTable.getEntry("speed :)");
    private final NetworkTableEntry m_targetLengthEntry = m_networkTable.getEntry("target length :(");
    private final NetworkTableEntry m_setExtendedEntry = m_networkTable.getEntry("extended ;)");
    private final NetworkTableEntry m_currentLengthEntry = m_networkTable.getEntry("current length >:(");
    private final NetworkTableEntry m_rawEncoderUnits = m_networkTable.getEntry("raw encoder units >:)");
    private final NetworkTableEntry m_solenoidValue = m_networkTable.getEntry("solenoid value :D");

    public ElevatorSub () {
        m_leftMotor = new TalonSRX(ElevatorConstants.ELEVATOR_1ID);
        m_rightMotor = new TalonSRX(ElevatorConstants.ELEVATOR_2ID);
        m_leftMotor.setInverted(true);
        //m_rightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        m_limitSwitch = new DigitalInput(ElevatorConstants.ELEVATOR_LIMIT_SWITCH_CHANNEL);
        m_doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, ElevatorConstants.FORWARD_CHANNEL, ElevatorConstants.REVERSE_CHANNEL);
        m_logger.setLevel(Level.INFO);
    }

    public void setSpeed(double speed) {
        if (m_limitSwitch.get() && speed < 0) { 
             speed = Math.max(0, speed);
        }
         else  if (getLength() <= ElevatorConstants.MAX_ELEVATOR_LENGTH && speed>0)   {
             speed = Math.min(speed, 0);
        } else{
        m_leftMotor.set(ControlMode.PercentOutput, speed);  
        m_rightMotor.set(ControlMode.PercentOutput, speed);
        }
        m_lastUsedPID = false;
        m_setSpeedEntry.setDouble(speed);
    }


    public void setLength(double length){
        if (! m_lastUsedPID) m_pid.reset();
        m_rightMotor.set(ControlMode.PercentOutput, m_pid.calculate(getLength(), length));
        m_lastUsedPID = true;
        m_logger.finest("set length: " + length);
        m_targetLengthEntry.setDouble(length);
    }

    public double getLength() {
        return m_rightMotor.getSelectedSensorPosition() / ElevatorConstants.TICKS_PER_REVOLUTION * ElevatorConstants.GEAR_RATIO * ElevatorConstants.DISTANCE_PER_REVOLUTION;
    }

    public boolean getElevatorExtended() {
        final boolean extendTrue = m_doubleSolenoid.get() == ElevatorConstants.EXTENDED;
        return extendTrue;
    }

    public void setExtended(boolean wantsExtended){
        m_doubleSolenoid.set(wantsExtended ? ElevatorConstants.EXTENDED : ElevatorConstants.RETRACTED);
        m_logger.info("set extended: " + wantsExtended);
        m_setExtendedEntry.setBoolean(wantsExtended);
    }

    public void toggleElevatorExtended(){
        setExtended(!getElevatorExtended());
    }

    @Override
    public void periodic() {
        if (m_limitSwitch.get()){
            m_rightMotor.setSelectedSensorPosition(0);
        } 
        m_currentLengthEntry.setDouble(getLength());
        m_rawEncoderUnits.setDouble(m_rightMotor.getSelectedSensorPosition());
        m_solenoidValue.setString(switch (m_doubleSolenoid.get()) {
            case kForward -> "Forward";
            case kReverse -> "Reverse";
            case kOff -> "Off";
        });
    }
}
