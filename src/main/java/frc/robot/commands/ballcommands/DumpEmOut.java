package frc.robot.commands.ballcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallSystem;
import edu.wpi.first.wpilibj.Timer;
import static frc.robot.Constants.*;

public class DumpEmOut extends CommandBase {
    /*
    * This command is used for when the ball system has 3 balls in the main loading area, and a fourth in the
    * belt. It dumps all of the contents into the goal in 1 button press
    *
     */
    private BallSystem ball;
    private Timer t;

    public DumpEmOut(BallSystem ball){
        this.ball = ball;
        this.t = new Timer();
        addRequirements(ball);
    }

    @Override
    public void initialize(){
        t.start();
        ball.spinFlywheel(flywheelSpeed);
        ball.spinIntakeMiddle(intakeSpeed, middleSpeed);

    }
    @Override
    public void execute(){

    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        t.stop();
        ball.spinFlywheel(0);
        ball.spinIntakeMiddle(0, 0);
    }

}
