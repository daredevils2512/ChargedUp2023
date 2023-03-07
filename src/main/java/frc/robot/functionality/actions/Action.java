package frc.robot.functionality.actions;

public interface Action {
    public void initialize();

    public void execute();

    public void onEnd();

    public boolean isFinished();
}
