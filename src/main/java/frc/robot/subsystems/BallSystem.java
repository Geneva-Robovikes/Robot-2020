package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import static frc.robot.Constants.*;

public class BallSystem extends SubsystemBase {
    // 3 motors - intake, middle, output
    // 2 functions - spin intake and middle at the same time; spin output
    private CANSparkMax intake;
    private Spark middle;
    private Spark output;

    public BallSystem(CANSparkMax intake, Spark middle, Spark output) {
        this.intake = intake;
        this.middle = middle;
        this.output = output;
    }

    public void spinIntakeMiddle(double i, double m) {
        intake.set(i);
        middle.set(m);
    }

    public void spinOutput(double o) {
        output.set(o);
    }
}
