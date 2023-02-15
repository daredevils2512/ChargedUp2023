package frc.robot.commands.auto;

import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.subsystems.AutoDriveSub;
import frc.robot.utils.Constants.Auto;
import frc.robot.utils.Constants.AutoDriveConstants;

public class TrajectoryMovement extends CommandBase {
    //Private finals
    private final AutoDriveSub auto_drive;
    private final RamseteController ramseteControl;
    private final Trajectory path;

    //Constructor
    public TrajectoryMovement(AutoDriveSub drivetrain, 
    Trajectory trajectory) {
        auto_drive = drivetrain;
        path = trajectory;
        ramseteControl = new RamseteController();

        Rotation2d rotationTolerance = new Rotation2d(Auto.AUTO__DEGREES_ERROR * Math.PI / 180);
        Translation2d transTolerance = new Translation2d(Auto.AUTO_ERROR, Auto.AUTO_ERROR);
        Pose2d poseTolerance = new Pose2d(transTolerance, rotationTolerance);
        ramseteControl.setTolerance(poseTolerance);    

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        new RamseteCommand(path, () -> auto_drive.getRobotPosition(), 
        ramseteControl, AutoDriveConstants.kinematics, auto_drive::useWheelSpeeds, auto_drive);    
    }


    @Override
    public void end(boolean interrupted) {
        auto_drive.clearSpeed();
    }

    public boolean interrupted() {
        return false;
    }
}
