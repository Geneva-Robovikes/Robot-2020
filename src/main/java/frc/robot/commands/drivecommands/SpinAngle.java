package frc.robot.commands.drivecommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class SpinAngle extends CommandBase {
    private Drive drive;
    private double goalAngle;
    private double originalAngle;
    private double difference;

    public SpinAngle(Drive drive, double angle){
        this.drive = drive;
        this.goalAngle = angle;
        addRequirements(drive);
    }

    @Override
    public void initialize(){
        // Mark where we are at the start of the command
        originalAngle = drive.getGyroAngle();
    }

    @Override
    public void execute(){
        // Spin clockwise or counter-clockwise depending on input angle
        drive.spin(goalAngle / Math.abs(goalAngle));

        // Determine how far the angle has displaced
        difference = Math.abs(drive.getGyroAngle() - originalAngle);
    }

    @Override
    public boolean isFinished(){
        // End if we have spun the desired amount
        return difference >= Math.abs(goalAngle);
    }

    @Override
    public void end(boolean interrupted){
        // Stop spinning when we finish
        super.end(interrupted);
        drive.spin(0);
    }
}
