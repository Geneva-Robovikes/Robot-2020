package frc.robot.commands.drivecommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.DashHelper;
import frc.robot.RobotStick;
import frc.robot.subsystems.Drive;

public class DriveMecanum extends CommandBase {
    private Drive drive;
    private RobotStick stick;

    private double kP = .05;
    private double kI = 0;
    private double kD = 0;

    private double originalAngle;
    private double previousError;
    public DriveMecanum(Drive drive, RobotStick stick){
        this.drive = drive;
        this.stick = stick;
        originalAngle = drive.getGyroAngle();
        previousError = 0;
        addRequirements(drive);
    }

    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        //kP = DashHelper.kP.getDouble(0.015);
        //kI = DashHelper.kI.getDouble(0);
        //kD = DashHelper.kD.getDouble(0);
        double x, y, z; //frontLeft, frontRight, backLeft, backRight;
        x = stick.getDX();
        y = stick.getDY();
        z = stick.getDZ();

        if(z == 0){
            // Drive Straight
            double error = drive.getZeroAngle() - drive.getGyroAngle();
            double turnPower = kP * error;
            drive.setMechDriveManual(x, y, turnPower);
            previousError = error;

        } else {
            // Drive according to joystick
            drive.setMechDriveManual(x, y, z);
            drive.setZeroAngle(drive.getGyroAngle() );
            previousError = 0;
        }
        //drive.setMechDrive(x, y, z);




    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }
}
