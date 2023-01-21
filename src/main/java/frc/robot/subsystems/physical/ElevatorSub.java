package frc.robot.subsystems.physical;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;

public class ElevatorSub extends SubsystemBase{
    private final TalonSRX leftMotor;
    private final TalonSRX rightMotor;
    private final Encoder encoder;
    private final DoubleSolenoid doubleSolenoid;
    private final Logger logger = Logger.getLogger(getName());

    public ElevatorSub () {
        rightMotor = new TalonSRX(Constants.ELEVATOR_1ID);
        leftMotor = new TalonSRX(Constants.ELEVATOR_2ID);

        leftMotor.follow(rightMotor);
        leftMotor.setInverted(true);

        encoder = new Encoder(Constants.ELEVATOR_ENCODER_CHANNEL1ID, Constants.ELEVATOR_ENCODER_CHANNEL2ID); 
        encoder.setDistancePerPulse(Constants.ENCODER_PER_PULSE_DISTANCE);

        doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.FORWARD_CHANNEL, Constants.REVERSE_CHANNEL);
    }
    public void setSpeed(double speed) {
        speed = Constants.ELEVATOR_SPEED;
    }

    public double elevatorLength() {
        return encoder.getDistance();
    }

    public boolean getElevatorExtended() {
        final boolean extendTrue = doubleSolenoid.get() == Constants.EXTENDED;

        logger.finest("get elevator extended:" + extendTrue);
        return extendTrue;
    }

    public void setElevatorExtended(boolean wantsExtended){
        doubleSolenoid.set(wantsExtended ? Constants.EXTENDED : Constants.RETRACTED);
    logger.info("set extended: " + wantsExtended);
    }

    public void toggleElevatorExtended(){
        setElevatorExtended(!getElevatorExtended());
    }
}
