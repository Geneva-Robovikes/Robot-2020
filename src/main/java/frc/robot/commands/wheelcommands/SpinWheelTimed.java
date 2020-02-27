package frc.robot.commands.wheelcommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WheelSpinner;

import static frc.robot.Constants.*;

public class SpinWheelTimed extends CommandBase {
    private WheelSpinner wheel;
    private double time;
    private Timer t;

    public SpinWheelTimed(WheelSpinner wheel, double time){
        this.wheel = wheel;
        this.time = time;
        addRequirements(wheel);
    }

    @Override
    public void initialize(){
        t.start();
        wheel.spinWheel(wheelSpinnerSpeed);
    }

    @Override
    public boolean isFinished(){
        return t.get() > time;
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        wheel.spinWheel(0);
        t.stop();
    }
}
