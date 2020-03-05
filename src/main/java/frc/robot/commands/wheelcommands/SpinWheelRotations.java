package frc.robot.commands.wheelcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WheelSpinner;

import static frc.robot.Constants.*;

public class SpinWheelRotations extends CommandBase {
    private WheelSpinner wheel;
    private String currentColor;
    private String previousColor;
    private double numRotations;
    private int counter;


    public SpinWheelRotations(WheelSpinner wheel, double numSpins){
        this.wheel = wheel;
        this.numRotations = numSpins;
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
        if (previousColor.equals("Red") && currentColor.equals("Yellow")) {
            counter++;
        }
        previousColor = currentColor;

    }

    @Override
    public boolean isFinished(){
        return counter == (numRotations * 2);
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        wheel.spinWheel(0);
        wheel.closeServo();
    }
}
