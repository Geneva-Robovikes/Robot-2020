package frc.robot.commands.liftcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lift;

public class PowerRightLift extends CommandBase {
    private Lift lift;

    public PowerRightLift(Lift lift){
        this.lift = lift;
    }

    @Override
    public void initialize(){

    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
    }

}
