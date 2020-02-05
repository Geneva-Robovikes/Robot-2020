package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallSystem;

public class BallIntakeMiddle extends CommandBase {
    private BallSystem ball;
    public BallIntakeMiddle(BallSystem ball){
        this.ball = ball;
    }

    @Override
    public void initialize(){
        ball.spinIntakeMiddle(1,1);
    }

    @Override
    public void execute(){

    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        ball.spinIntakeMiddle(0, 0);

    }

}
