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

    public void setMechDrive(double x, double y, double z){
        mechDrive.driveCartesian(xSpeed * x, ySpeed * y, zSpeed * z);
    }

    public void spin(double speed){
        setDriveVictors(speed, speed, speed, speed);
    }

    public double getGyroAngle(){
        return gyro.getAngle();
    }


}