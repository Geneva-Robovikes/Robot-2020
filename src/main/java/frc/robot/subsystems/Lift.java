package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Lift extends SubsystemBase {
    private Talon lift1;
    private Talon lift2;
    public Lift(Talon l1, Talon l2){
        lift1 = l1;
        lift2 = l2;
    }

    public void setLift(double liftSpeed){
        lift1.set(liftSpeed);
        lift2.set(-liftSpeed);
    }
}
