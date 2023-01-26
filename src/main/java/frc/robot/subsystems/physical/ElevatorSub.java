package frc.robot.subsystems.physical;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;

public class ElevatorSub extends SubsystemBase{
    private final TalonSRX m_backMotor;
    private final TalonSRX m_frontMotor;
    private final Encoder m_encoder;
    private final DigitalInput m_limitSwitch;
    private final DoubleSolenoid m_doubleSolenoid;
    private final PIDController m_pid = new PIDController(0, 0, 0);
    private boolean m_lastUsedPID = false;
    private final Logger m_logger = Logger.getLogger(getName());

    public ElevatorSub () {
        m_frontMotor = new TalonSRX(Constants.ELEVATOR_1ID);
        m_backMotor = new TalonSRX(Constants.ELEVATOR_2ID);

        m_backMotor.follow(m_frontMotor);
        m_backMotor.setInverted(true);

        m_encoder = new Encoder(Constants.ELEVATOR_ENCODER_CHANNEL1ID, Constants.ELEVATOR_ENCODER_CHANNEL2ID); 
        m_encoder.setDistancePerPulse(Constants.ENCODER_PER_PULSE_DISTANCE);

        m_frontMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);


        m_limitSwitch = new DigitalInput(Constants.ELEVATOR_LIMIT_SWITCH_CHANNEL);

        m_doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.FORWARD_CHANNEL, Constants.REVERSE_CHANNEL);
    }
    public void setSpeed(double speed) {
        m_frontMotor.set(ControlMode.PercentOutput, m_limitSwitch.get() ? Math.max(0, speed) : speed);
        m_lastUsedPID = false;
    }

    public void setLength(double length){
        if (m_lastUsedPID == false) m_pid.reset();
        m_frontMotor.set(ControlMode.PercentOutput, m_pid.calculate(getLength(), length));
        m_lastUsedPID = true;
    }

    public double getLength() {
        return m_frontMotor.getSelectedSensorPosition() / Constants.TICKS_PER_REVOLUTION * Constants.GEAR_RATIO * Constants.DISTANCE_PER_REVOLUTION;
    }

    public boolean getElevatorExtended() {
        final boolean extendTrue = m_doubleSolenoid.get() == Constants.EXTENDED;

        m_logger.finest("get elevator extended:" + extendTrue);
        return extendTrue;
    }

    public void setElevatorExtended(boolean wantsExtended){
        m_doubleSolenoid.set(wantsExtended ? Constants.EXTENDED : Constants.RETRACTED);
    m_logger.info("set extended: " + wantsExtended);
    }

    public void toggleElevatorExtended(){
        setElevatorExtended(!getElevatorExtended());
    }

    @Override
    public void periodic() {   
        
    }
}
