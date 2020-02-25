package frc.robot.commands.liftcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lift;

import static frc.robot.Constants.*;

public class LiftUp extends CommandBase {
    private Lift lift;
    public LiftUp(Lift lift){
        this.lift = lift;
        addRequirements(lift);
    }

    @Override
    public void initialize(){
        lift.setLift(liftSpeed);
    }

    @Override
    public void execute(){

    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        lift.setLift(0);
    }
}
