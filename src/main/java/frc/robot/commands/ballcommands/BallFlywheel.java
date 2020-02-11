package frc.robot.commands.ballcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallSystem;
import static frc.robot.Constants.*;

public class BallFlywheel extends CommandBase {
    private BallSystem ball;
    public BallFlywheel(BallSystem ball){
        this.ball = ball;
    }

    @Override
    public void initialize(){
        ball.spinFlywheel(flywheelSpeed);
    }

    @Override
    public void execute(){

    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        ball.spinFlywheel(0);

    }
}
