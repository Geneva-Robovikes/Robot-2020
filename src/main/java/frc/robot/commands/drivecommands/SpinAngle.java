package frc.robot.commands.drivecommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.DashHelper;
import frc.robot.subsystems.Drive;

public class SpinAngle extends CommandBase {
    private Drive drive;
    private final double tolerance = 1;

    private double kP;
    private double kI;
    private double kD;

    private double goalAngle;
    private double error;
    private double previousError;

    private double integral;

    public SpinAngle(Drive drive, double angle){
        this.drive = drive;
        this.goalAngle = angle;
        addRequirements(drive);

    }

    @Override
    public void initialize(){
        kP = DashHelper.kP.getDouble(0.01);
        kI = DashHelper.kI.getDouble(0);
        kD = DashHelper.kD.getDouble(0);

        // Find the setpoint
        goalAngle += drive.getGyroAngle();

        previousError = drive.getGyroAngle() - goalAngle;

        integral = 0;
    }

    @Override
    public void execute(){
        error = goalAngle - drive.getGyroAngle();
        integral += (error * 0.02);

        double turnPower = kP * error + kD * ((error - previousError) / 0.02) + kI * integral;

        drive.spin(turnPower);

        // Reset the previous error
        previousError = error;
    }

    @Override
    public boolean isFinished(){
        // End if we have spun the desired amount
        return (error <= tolerance) && (error >= -tolerance);
    }

    @Override
    public void end(boolean interrupted){
        // Stop spinning when we finish
        super.end(interrupted);
        drive.spin(0);
        drive.setZeroAngle(drive.getGyroAngle());
    }

}
