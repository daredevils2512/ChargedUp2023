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
    private final TalonSRX  rightMotor;
    private final TalonSRX   leftMotor;
    private final DigitalInput  limitSwitch;
    private final DoubleSolenoid  doubleSolenoid;
    private final PIDController  pid = new PIDController(0, 0, 0);
    private boolean  lastUsedPID = false;
    private final Logger  logger = Logger.getLogger(getName());
    private final NetworkTable  networkTable = NetworkTableInstance.getDefault().getTable(getName());
    private final NetworkTableEntry  setSpeedEntry =  networkTable.getEntry("speed :)");
    private final NetworkTableEntry  targetLengthEntry =  networkTable.getEntry("target length :(");
    private final NetworkTableEntry  setExtendedEntry =  networkTable.getEntry("extended ;)");
    private final NetworkTableEntry  currentLengthEntry =  networkTable.getEntry("current length >:(");
    private final NetworkTableEntry  rawEncoderUnits =  networkTable.getEntry("raw encoder units >:)");
    private final NetworkTableEntry  solenoidValue =  networkTable.getEntry("solenoid value :D");

    public ElevatorSub () {
         leftMotor = new TalonSRX(ElevatorConstants.ELEVATOR_1ID);
         rightMotor = new TalonSRX(ElevatorConstants.ELEVATOR_2ID);
         leftMotor.setInverted(true);
         rightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
         limitSwitch = new DigitalInput(ElevatorConstants.ELEVATOR_LIMIT_SWITCH_CHANNEL);
         doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, ElevatorConstants.FORWARD_CHANNEL, ElevatorConstants.REVERSE_CHANNEL);
         logger.setLevel(Level.INFO);
    }

    public void setSpeed(double speed) {
        if ( limitSwitch.get() && speed < 0) { 
              leftMotor.set(ControlMode.PercentOutput, 0);  
              rightMotor.set(ControlMode.PercentOutput, 0);
        }
         else  if (getLength() <= ElevatorConstants.MAX_ELEVATOR_LENGTH && speed>0)   {
             speed = Math.min(speed, 0);
              leftMotor.set(ControlMode.PercentOutput, 0);  
              rightMotor.set(ControlMode.PercentOutput, 0);
        } else{
         leftMotor.set(ControlMode.PercentOutput, speed);  
         rightMotor.set(ControlMode.PercentOutput, speed);
        }
         lastUsedPID = false;
         setSpeedEntry.setDouble(speed);
    
    }


    public void setLength(double length){
        if (!  lastUsedPID)  pid.reset();
         leftMotor.set(ControlMode.PercentOutput,  pid.calculate(getLength(), length));
         lastUsedPID = true;
         logger.finest("set length: " + length);
         targetLengthEntry.setDouble(length);
    }

    public double getLength() {
        return  rightMotor.getSelectedSensorPosition() / ElevatorConstants.TICKS_PER_REVOLUTION * ElevatorConstants.GEAR_RATIO * ElevatorConstants.DISTANCE_PER_REVOLUTION;
    }

    public boolean getElevatorExtended() {
        final boolean extendTrue =  doubleSolenoid.get() == ElevatorConstants.EXTENDED;
        return extendTrue;
    }

    public void setExtended(boolean wantsExtended){
         doubleSolenoid.set(wantsExtended ? ElevatorConstants.EXTENDED : ElevatorConstants.RETRACTED);
         logger.info("set extended: " + wantsExtended);
         setExtendedEntry.setBoolean(wantsExtended);
    }

    public void toggleElevatorExtended(){
        setExtended(!getElevatorExtended());
    }

    @Override
    public void periodic() {
        if ( limitSwitch.get()){
             rightMotor.setSelectedSensorPosition(0);
        } 
         currentLengthEntry.setDouble(getLength());
         rawEncoderUnits.setDouble( rightMotor.getSelectedSensorPosition());
         solenoidValue.setString(switch ( doubleSolenoid.get()) {
            case kForward -> "Forward";
            case kReverse -> "Reverse";
            case kOff -> "Off";
        });
    }
}
