package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotStick;
import frc.robot.subsystems.Drive;

public class DriveMecanum extends CommandBase {
    private Drive drive;
    private RobotStick stick;
    public DriveMecanum(Drive drive, RobotStick stick){
        this.drive = drive;
        this.stick = stick;
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
        /*frontLeft = x + y + z;
        frontRight = x - y + z;
        backLeft = -x + y + z;
        backRight = -x - y + z;

        drive.setDriveVictors(frontLeft, frontRight, backLeft, backRight);*/
        drive.setMechDrive(x, y, z);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }
}
