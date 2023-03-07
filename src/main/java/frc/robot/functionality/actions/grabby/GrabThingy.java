package frc.robot.functionality.actions.grabby;

import frc.robot.functionality.actions.Action;
import frc.robot.subsystems.GrabbySub;

public class GrabThingy implements Action {
    private final GrabbySub grabby;

    public GrabThingy(GrabbySub grabby) {
        this.grabby = grabby;
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
    public void onEnd() {
      
    }

    /** Returns true when the command should end. */
    @Override
    public boolean isFinished() {
      return true;
  }
    
}
