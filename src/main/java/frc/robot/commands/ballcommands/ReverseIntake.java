package frc.robot.commands.ballcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallSystem;

import static frc.robot.Constants.*;


public class ReverseIntake extends CommandBase {
    private BallSystem ball;
    public ReverseIntake(BallSystem ball){
        this.ball = ball;
        addRequirements(ball);
    }

    @Override
    public void initialize(){
        ball.spinIntakeMiddle(-intakeSpeed, 0);
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
