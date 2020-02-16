package frc.robot.commands.drivecommands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.DashHelper;
import frc.robot.subsystems.Drive;

public class SpinAngle extends CommandBase {
    private Drive drive;
    private final double tolerance = 1;
    private PIDController pidController;

    private double kP;
    private double kI;
    private double kD;

    // Zeigler-Nichols constants
    private double tU = .722;
    private double kU = .1683;

    //private double tU = .715;
    //private double kU = .1683;



    private double goalAngle;
    private double error;
    private double previousError;

    private double proportional;
    private double integral;
    private double derivative;

    public SpinAngle(Drive drive, double angle){
        this.drive = drive;
        this.goalAngle = angle;
        addRequirements(drive);

    }

    @Override
    public void initialize(){
        //kP = DashHelper.kP.getDouble(0.05);
        //kI = DashHelper.kI.getDouble(0);
        //kD = DashHelper.kD.getDouble(0);

        // PI
//        kP = .45 * kU;
//        kI = (.54 * kU)/tU;
//        kD = 0;

        // PID Constants
        kP = .6 * kU;
        kI = (1.2 * kU)/tU;
        kD = (3 * kU *tU)/40;


        // PIDController object to help us with the math
        pidController = new PIDController(kP, kI, kD);
        pidController.setTolerance(tolerance);

        // Set the setpoint to where you are + angle to spin
        drive.setZeroAngle(drive.getGyroAngle() + goalAngle);

        //integral = 0;
        //derivative = 0;
        //proportional = 0;

    }

    @Override
    public void execute(){
        error = drive.getZeroAngle() - drive.getGyroAngle();

        /*proportional = error;
        integral += 0.02 * error;
        derivative = (error-previousError)/0.02;

        double turnPower = (kP * proportional) + (kI * integral) + (kD * derivative);
        */
        // Use PIDController to calculate necessary speed
        double turnPID = pidController.calculate(drive.getGyroAngle(), drive.getZeroAngle());

        drive.spin(turnPID);

        // Reset the previous error
        //previousError = error;
    }

    @Override
    public boolean isFinished(){
        // End if we have spun the desired amount
        System.out.println(error);
        return (error <= tolerance) && (error >= -tolerance);
    }

    @Override
    public void end(boolean interrupted){
        //System.out.println("SpinAngle Command Ended" + interrupted);
        // Stop spinning when we finish
        super.end(interrupted);
        drive.spin(0);
        // Reset zero so DriveMecanum command doesn't undo the spin
        drive.setZeroAngle(drive.getGyroAngle());
    }

}
