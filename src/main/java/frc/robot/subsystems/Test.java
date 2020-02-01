package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Test extends SubsystemBase {
    private CANSparkMax testSpark;

    public Test(CANSparkMax test){
        this.testSpark = test;
    }

    public void setVictorToMax(){
        testSpark.set(.75);
    }

    public void turnOffVictor(){
        testSpark.set(0);
    }

    public double getSpark(){
        return testSpark.get();
    }
}
