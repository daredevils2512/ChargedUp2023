package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PigeonSub;
import frc.robot.subsystems.interfaces.Drivetrain;
import frc.robot.utils.Constants;

public class OrientateAndDrive extends CommandBase {
    private final Drivetrain drivetrain;
    private final PigeonSub gyro;
    private double startYaw;

    public OrientateAndDrive(Drivetrain drivetrain, PigeonSub gyro) {
        this.drivetrain = drivetrain;
        this.gyro = gyro;
        addRequirements(drivetrain, gyro);
    }

    @Override
    public void initialize() {
        startYaw = gyro.getYaw();
    }

    @Override
    public void execute() {
        // turn left -> yaw+
        if (Constants.AUTO_DESIRED_YAW > startYaw) {
            drivetrain.arcadeDrive(0.0, 1.0);
        }

    }

    @Override
    public void end(boolean interrupted) {

    }

}
