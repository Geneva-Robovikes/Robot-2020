package frc.robot.commands.ballcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.DashHelper;
import frc.robot.subsystems.BallSystem;

public class FlipServo extends CommandBase {
    private BallSystem ball;
    private int n;

    public FlipServo(BallSystem ball){
        this.ball = ball;
    }

    @Override
    public void initialize(){
        n =0;
        if(ball.isServoClosed()){
            ball.openServo();
            DashHelper.sbServoOpen.setBoolean(true);
        } else {
            ball.closeServo();
            DashHelper.sbServoOpen.setBoolean(false);
        }
    }

    @Override
    public void execute(){
        n++;
    }

    @Override
    public boolean isFinished(){
        return n >= 2;
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
    }


}
