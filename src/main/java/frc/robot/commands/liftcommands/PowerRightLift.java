package frc.robot.commands.liftcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotStick;
import frc.robot.subsystems.Lift;

import static frc.robot.Constants.*;

public class PowerRightLift extends CommandBase {
    private Lift lift;
    private RobotStick stick;

    public PowerRightLift(Lift lift, RobotStick stick){
        this.lift = lift;
        this.stick = stick;
    }

    @Override
    public void initialize(){
        lift.setRightLift(-stick.getSlider());
    }

    @Override
    public void execute(){
        lift.setRightLift(-stick.getSlider());
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        lift.setRightLift(0);
    }

}
