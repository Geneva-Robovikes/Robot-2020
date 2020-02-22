package frc.robot.subsystems;
import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.DashHelper;

import static frc.robot.Constants.*;

public class Drive extends SubsystemBase{

    private VictorSPX frontLeft;
    private VictorSPX frontRight;
    private VictorSPX backLeft;
    private VictorSPX backRight;

    private ADIS16448_IMU gyro;

    private MecanumDrive mechDrive;

    private double zeroAngle = 0;
    private double actualAngle = 0;

    public Drive(VictorSPX frontLeft, VictorSPX frontRight, VictorSPX backLeft, VictorSPX backRight,
                 ADIS16448_IMU gyro, MecanumDrive mechDrive){
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;
        this.gyro = gyro;
        this.mechDrive = mechDrive;
        DashHelper.getInstance().setUpMechDriveWidget(mechDrive);
        DashHelper.getInstance().setUpGyroWidget(gyro);
    }

    public void setDriveVictors(double fL, double fR, double bL, double bR){
        frontLeft.set(ControlMode.PercentOutput, fL);
        frontRight.set(ControlMode.PercentOutput, fR);
        backLeft.set(ControlMode.PercentOutput, bL);
        backRight.set(ControlMode.PercentOutput, bR);
    }

    public void setMechDriveManual(double x, double y, double z){
        mechDrive.driveCartesian(xSpeed * x, ySpeed * y, zSpeedManual * z);
    }

    public void setMechDriveAutomatic(double x, double y, double z){
        mechDrive.driveCartesian(xSpeed * x, ySpeed * y, zSpeedAuto * z);
    }

    public void spin(double speed){
        setMechDriveAutomatic(0, 0, speed);
    }

    public double getGyroAngle(){
        return actualAngle;
    }

    public double getGyroRate(){
        return gyro.getRate();
    }

    public double getZeroAngle(){
        return this.zeroAngle;
    }

    public void setZeroAngle(double angle){
        zeroAngle = angle;
    }

    public void resetGyro(){
        gyro.reset();
        // gyro.calibrate();
    }

    @Override
    public void periodic() {
        // Code to avoid gyro drift
        if (gyro.getRate() >= gyroDeadZone || gyro.getRate() <= -gyroDeadZone) { // Deadzone; prevents slight input
            //System.out.println(gyro.getRate());
            actualAngle += (gyro.getRate() * .02);
        }

    }


}