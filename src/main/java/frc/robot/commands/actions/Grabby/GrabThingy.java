package frc.robot.commands.actions.Grabby;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GrabbySub;

public class GrabThingy extends CommandBase {
    private final GrabbySub grabby;

    public GrabThingy(GrabbySub grabby) {
        this.grabby = grabby;

        addRequirements(this.grabby);
    }

    /** Called when the command starts */
    @Override
    public void initialize() { 

    }

    /** Called every time the scheduler runs while the command is scheduled. */
    @Override
    public void execute() {
        grabby.toggleGrab();
    }

    /** Called once the command ends or is interrupted. */
    @Override
    public void end(boolean interrupted) {
      
    }

    /** Returns true when the command should end. */
    @Override
    public boolean isFinished() {
      return true;
  }
    
}
