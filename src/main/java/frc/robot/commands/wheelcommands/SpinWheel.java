package frc.robot.commands.wheelcommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.WheelSpinner;

import static frc.robot.Constants.*;

public class SpinWheel extends CommandBase {
    private WheelSpinner wheel;
    public SpinWheel(WheelSpinner spinner){
        this.wheel = spinner;
        addRequirements(wheel);
    }

    @Override
    public void initialize(){
        wheel.openServo();
        //CommandScheduler.getInstance().schedule(new FlipColorSensorServo(wheel));
        if(DriverStation.getInstance().getGameSpecificMessage() != null){
            CommandScheduler.getInstance().schedule(new SpinWheelColor(wheel));
        } else{
            CommandScheduler.getInstance().schedule(new SpinWheelRotations(wheel));
        }
    }

    @Override
    public void execute(){

    }

    @Override
    public boolean isFinished(){
        return true;
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        //wheel.spinWheel(0);
    }

}
