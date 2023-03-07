package frc.robot.functionality;

import frc.robot.functionality.commands.Command;

public class CommandExecutor {
    private final Thread thread;

    public CommandExecutor(Command autoMode) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (autoMode != null) {
                    autoMode.routine();
                }
            }
        });
        
    }

    public void execute() {
        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }
    
}
