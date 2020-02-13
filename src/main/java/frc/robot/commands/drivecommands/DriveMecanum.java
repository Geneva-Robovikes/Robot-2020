package frc.robot.commands.drivecommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotStick;
import frc.robot.subsystems.Drive;

public class DriveMecanum extends CommandBase {
    private Drive drive;
    private RobotStick stick;

    private final double kP = .06;
    private final double kD = .1;

    private double originalAngle;
    private double previousError;
    public DriveMecanum(Drive drive, RobotStick stick){
        this.drive = drive;
        this.stick = stick;
        originalAngle = drive.getGyroAngle();
        previousError = drive.getGyroAngle() - originalAngle;
        addRequirements(drive);
    }

    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double x, y, z; //frontLeft, frontRight, backLeft, backRight;
        x = stick.getDX();
        y = stick.getDY();
        z = stick.getDZ();

        if(z == 0){
            // Drive Straight
            double error = -(drive.getGyroAngle() - originalAngle);
            double turn_power = kP * error + kD * ((error - previousError) / 0.02);
            drive.setMechDrive(x, y, turn_power);
            previousError = error;

        } else {
            // Drive according to joystick
            drive.setMechDrive(x, y, z);
            originalAngle = drive.getGyroAngle();
        }
        //drive.setMechDrive(x, y, z);




    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }
}
