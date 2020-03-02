package frc.robot.commands.liftcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotStick;
import frc.robot.subsystems.Lift;

import static frc.robot.Constants.*;

public class PowerLeftLift extends CommandBase {
    private Lift lift;
    private RobotStick stick;

    public PowerLeftLift(Lift lift, RobotStick stick){
        this.lift = lift;
        this.stick = stick;
    }

    @Override
    public void initialize(){
        lift.setLeftLift(-stick.getSlider());
    }

    @Override
    public void execute(){
        lift.setLeftLift(-stick.getSlider());
    }


    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        lift.setLeftLift(0);
    }

}
