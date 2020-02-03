package frc.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.util.Color;

public class DashHelper {
    public ShuffleboardTab mainDash;
    public static NetworkTableEntry sbSpeedTest;
    public static NetworkTableEntry sbGyroWidget;
    public static NetworkTableEntry sbEncoderDistance;
    public static NetworkTableEntry sbRedValue, sbGreenValue, sbBlueValue;
    public static NetworkTableEntry sbTimer;

    public void startDash(){
        mainDash = Shuffleboard.getTab("Main");
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setVideoMode(VideoMode.PixelFormat.kMJPEG, 800, 600, 20 );
        camera.setExposureAuto();
        mainDash.add("Camera", camera);
        sbSpeedTest = mainDash.add("Speed", Constants.kSpeed).getEntry();


        sbTimer = mainDash.add("Timer", 0).getEntry();

        sbEncoderDistance = mainDash.add("Encoder", 0).getEntry();
        sbRedValue = mainDash.add("Red Value", 0).getEntry();
        sbGreenValue = mainDash.add("Green Value", 0).getEntry();
        sbBlueValue = mainDash.add("Blue Value", 0).getEntry();
        Shuffleboard.selectTab("Main");
        Shuffleboard.startRecording();

    }

    public void setEncoder(double distance){
        sbEncoderDistance.setDouble(distance);
    }

    public void setColor(Color color){
        sbRedValue.setDouble(color.red);
        sbGreenValue.setDouble(color.green);
        sbBlueValue.setDouble(color.blue);
    }

    public void setUpGyroWidget(ADXRS450_Gyro gyro){
        mainDash.add("Gyro", gyro).withWidget(BuiltInWidgets.kGyro);
    }

    public void setUpMechDriveWidget(MecanumDrive mechDrive){
        mainDash.add("Mecanum Drive", mechDrive).withWidget(BuiltInWidgets.kMecanumDrive);
    }

    public void setTimer(Timer timer){
        sbTimer.setDouble(timer.get());
    }

}