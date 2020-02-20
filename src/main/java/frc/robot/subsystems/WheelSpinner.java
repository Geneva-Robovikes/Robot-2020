package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.SparkMax;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class WheelSpinner extends SubsystemBase {
    private ColorSensorV3 colorSensor;
    private CANSparkMax wheel;


    public WheelSpinner(CANSparkMax motor, ColorSensorV3 sensor){
        this.wheel = motor;
        this.colorSensor = sensor;
    }

    public Color getColor(){
        return colorSensor.getColor();
    }

    public void spinWheel(double wheelSpeed){
        wheel.set(wheelSpeed);
    }

    public int getRedValue(){
        return colorSensor.getRed();
    }
    public int getGreenValue(){
        return colorSensor.getGreen();
    }
    public int getBlueValue(){
        return colorSensor.getBlue();
    }
}
