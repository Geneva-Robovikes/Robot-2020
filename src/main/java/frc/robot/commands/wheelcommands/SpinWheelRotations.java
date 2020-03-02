package frc.robot.commands.wheelcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WheelSpinner;

import static frc.robot.Constants.*;

public class SpinWheelRotations extends CommandBase {
    private WheelSpinner wheel;
    private String currentColor;
    private String previousColor;
    private int counter;
    public SpinWheelRotations(WheelSpinner wheel){
        this.wheel = wheel;
        addRequirements(wheel);
    }

    @Override
    public void initialize(){
        wheel.spinWheel(wheelSpinnerSpeed);
        previousColor = wheel.getColorMatch();
    }

    @Override
    public void execute(){
        currentColor = wheel.getColorMatch();
        if (previousColor == "Yellow" && currentColor == "Red") {
            counter ++;
        }
        previousColor = currentColor;

    }

    @Override
    public boolean isFinished(){
        if (counter == 8) {
            return true;
        } else{
            return false;
        }
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        wheel.spinWheel(0);
        wheel.closeServo();
    }
}
