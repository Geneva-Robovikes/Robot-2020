package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Lift extends SubsystemBase {
    private Talon liftLeft;
    private Talon liftRight;
    public Lift(Talon left, Talon right){
        liftLeft = left;
        liftRight = right;
    }


    public void setLeftLift(double speed){
        liftLeft.set(speed);
    }

    public void setRightLift(double speed){
        liftRight.set(-speed);
    }
}
