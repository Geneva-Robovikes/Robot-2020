package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.Servo;
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
    private CANSparkMax middle;
    private Spark flywheel1;
    private Spark flywheel2;
    private Servo servoGate;

    public BallSystem(CANSparkMax intake, CANSparkMax middle, Spark flywheel1, Spark flywheel2, Servo servo) {
        this.intake = intake;
        this.middle = middle;
        this.flywheel1 = flywheel1;
        this.flywheel2 = flywheel2;
        this.servoGate = servo;
        this.closeServo();
    }

    public void spinIntakeMiddle(double intakeSpeed, double middleSpeed) {
        intake.set(intakeSpeed);
        middle.set(middleSpeed);
    }

    public void spinFlywheel(double flywheelSpeed) {
        flywheel1.set(flywheelSpeed);
        flywheel2.set(-flywheelSpeed);
    }

    public void openServo(){
        servoGate.setAngle(90);
    }

    public void closeServo(){
        servoGate.setAngle(0);
    }

    public double getServoAngle(){
        return servoGate.getAngle();
    }
}
