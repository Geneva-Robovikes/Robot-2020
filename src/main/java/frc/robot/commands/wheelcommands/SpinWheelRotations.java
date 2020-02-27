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
        previousColor = wheel.getColorMatch();
        wheel.spinWheel(wheelSpinnerSpeed);
    }

    @Override
    public void execute(){
        if (previousColor == "Yellow" ) {

        }
    }

    @Override
    public boolean isFinished(){

    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        wheel.spinWheel(0);
    }
}
