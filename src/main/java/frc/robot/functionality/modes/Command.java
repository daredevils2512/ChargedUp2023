package frc.robot.functionality.modes;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.functionality.actions.Action;
import frc.robot.utils.Constants.AutoConstants;

public abstract class Command {

    public void initialize() { }

    public void runAction(Action autoAction) {
        Timer t = new Timer();
        
        do {
            t.advanceIfElapsed(AutoConstants.AUTO_WAIT_TIME);

            autoAction.execute();
        } while (!autoAction.isFinished());
    }

    public void onEnd() { }

    public boolean isFinished() {
        return true;
    }

    public void routine() { }
    
}