package frc.robot.commands.drivecommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

import java.util.concurrent.TimeUnit;

public class DriveForward extends CommandBase {
    private Timer timer;
    private Drive driveSubsystem;
    private double speed;
    private double time;
    public DriveForward(Drive drive, double speed, double time) {
        driveSubsystem = drive;
        this.speed = speed;
        this.time = time;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        timer.start();
    }

    @Override
    public void execute() {
        driveSubsystem.setDriveVictors(speed, -speed, speed, -speed);
    }

    @Override
    public boolean isFinished() {
        return (timer.get() > time);

    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        driveSubsystem.setDriveVictors(0,0,0,0);
    }
}
