package frc.robot.commands.ballcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.DashHelper;
import frc.robot.subsystems.BallSystem;

public class FlipServo extends CommandBase {
    private BallSystem ball;

    public FlipServo(BallSystem ball){
        this.ball = ball;
    }

    @Override
    public void initialize(){
        if(ball.getServoAngle() == 90){
            ball.closeServo();
            DashHelper.sbServoOpen.setBoolean(false);
        } else {
            ball.openServo();
            DashHelper.sbServoOpen.setBoolean(true);
        }
    }

    @Override
    public boolean isFinished(){
        return true;
    }


}