package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Test;

public class TestCommand extends CommandBase {
    private Test test;

    public TestCommand(Test test){
        this.test = test;
        addRequirements(test);
    }
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        test.setVictorToMax();
        System.out.println("Command started: " + test.getSpark());
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        System.out.println("Command executing: " + test.getSpark());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        test.turnOffVictor();
    }

}
