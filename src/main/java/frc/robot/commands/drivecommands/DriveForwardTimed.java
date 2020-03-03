package frc.robot.commands.drivecommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

import java.util.concurrent.TimeUnit;

public class DriveForwardTimed extends CommandBase {
    private Timer timer;
    private Drive drive;
    private double speed;
    private double time;
    private double kP = 0.05;
    private final double spinConstant = 0.125;
    private double previousError;

    public DriveForwardTimed(Drive drive, double speed, double time) {
        this.drive = drive;
        this.speed = speed;
        this.time = time;
        timer = new Timer();
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        timer.reset();
        previousError = 0;
        timer.start();
    }

    @Override
    public void execute() {
        double error = drive.getZeroAngle() - drive.getGyroAngle();
        double turnPower = kP * error;
        drive.setMechDriveAutomatic(0, speed, turnPower);
        previousError = error;
    }

    @Override
    public boolean isFinished() {
        return (timer.get() > time);

    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        drive.setDriveVictors(0,0,0,0);
        drive.setMechDriveAutomatic(0,0, 0);
    }
}
