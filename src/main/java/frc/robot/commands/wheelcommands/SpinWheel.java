package frc.robot.commands.wheelcommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.WheelSpinner;

import static frc.robot.Constants.*;

public class SpinWheel extends CommandBase {
    private WheelSpinner wheel;
    private Timer t;
    private boolean done;

    public SpinWheel(WheelSpinner spinner){
        this.wheel = spinner;
        t = new Timer();
        addRequirements(wheel);
    }

    @Override
    public void initialize(){
        t.reset();
        t.start();
        wheel.openServo();

        //CommandScheduler.getInstance().schedule(new FlipColorSensorServo(wheel));

    }

    @Override
    public void execute(){
        if(t.get() > .5){
            if(!DriverStation.getInstance().getGameSpecificMessage().equals("")){
                CommandScheduler.getInstance().schedule(new SpinWheelRotations(wheel, 1));
                CommandScheduler.getInstance().schedule(new SpinWheelColor(wheel));
            } else{
                CommandScheduler.getInstance().schedule(new SpinWheelRotations(wheel, 3.5));
            }
            done = true;
        }

    }

    @Override
    public boolean isFinished(){
        return done;
    }

    @Override
    public void end(boolean interrupted){
        t.stop();
        super.end(interrupted);
        //wheel.spinWheel(0);
    }

}
