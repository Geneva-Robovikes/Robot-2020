package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Test extends SubsystemBase {
    private VictorSPX test;

    public Test(VictorSPX test){
        this.test = test;
    }

    public void setVictorToMax(){
        test.set(ControlMode.PercentOutput, .1);
    }

    public void turnOffVictor(){
        test.set(ControlMode.PercentOutput, 0);
    }
}
