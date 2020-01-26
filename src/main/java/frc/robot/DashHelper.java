package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.networktables.NetworkTableEntry;

public class DashHelper {
    public ShuffleboardTab mainDash;
    public static NetworkTableEntry sbSpeedTest;
    public static NetworkTableEntry sbGyroAngle;
    public static NetworkTableEntry sbEncoderDistance;

    public void startDash(){
        mainDash = Shuffleboard.getTab("Main");
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setVideoMode(VideoMode.PixelFormat.kMJPEG, 400, 300, 15 );
        camera.setExposureAuto();
        mainDash.add("Camera", camera);
        sbSpeedTest = mainDash.add("Speed", Constants.kSpeed).getEntry();
        sbGyroAngle = mainDash.add("Gyro Angle", 0).getEntry();
        sbEncoderDistance = mainDash.add("Encoder", 0).getEntry();
        Shuffleboard.selectTab("Main");
        Shuffleboard.startRecording();

    }

    public void setEncoder(double distance){
        sbEncoderDistance.setDouble(distance);
    }

}