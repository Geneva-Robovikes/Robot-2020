package frc.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.DashHelper;

import static frc.robot.Constants.*;

public class WheelSpinner extends SubsystemBase {
    private ColorSensorV3 colorSensor;
    private CANSparkMax wheel;
    private Servo sensorServo;
    private ColorMatch colorMatcher;
    private ColorMatchResult match;


    public WheelSpinner(CANSparkMax motor, ColorSensorV3 sensor, Servo servo){
        this.wheel = motor;
        this.colorSensor = sensor;
        this.sensorServo = servo;
        closeServo();
        colorMatcher = new ColorMatch();
        colorMatcher.addColorMatch(blueTarget);
        colorMatcher.addColorMatch(greenTarget);
        colorMatcher.addColorMatch(redTarget);
        colorMatcher.addColorMatch(yellowTarget);
    }

    public double getServoAngle(){
        return sensorServo.getAngle();
    }

    public void openServo(){
        sensorServo.setAngle(90);
    }

    public void closeServo(){
        sensorServo.setAngle(0);
    }


    public void spinWheel(double wheelSpeed){
        wheel.set(wheelSpeed);
    }

    public Color getColor(){
        return colorSensor.getColor();
    }

    public String getColorMatch(){
        String ret;
        if(match.color == blueTarget){
            ret = "Blue";
        } else if(match.color == greenTarget){
            ret = "Green";
        } else if(match.color == redTarget){
            ret = "Red";
        } else if(match.color == yellowTarget){
            ret = "Yellow";
        } else{
            ret = "Unkown";
        }
        //System.out.println(ret);
        return ret;

    }

    public double getColorMatchConfidence(){
        //System.out.println(match.confidence);
        return match.confidence;
    }


    @Override
    public void periodic(){
        match = colorMatcher.matchClosestColor(getColor());
        //DashHelper.getInstance().setColor(getColor());
        getColorMatch();
        getColorMatchConfidence();
    }
}
