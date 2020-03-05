package frc.robot.commands.wheelcommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WheelSpinner;

import static frc.robot.Constants.*;

public class SpinWheelColor extends CommandBase {
    private WheelSpinner wheel;
    private String ourColor;
    private String currentColor;
    private String previousColor;
    private int rotateCounter;
    private int colorCounter;
    private boolean rotated;

    public SpinWheelColor(WheelSpinner wheel){
        this.wheel = wheel;
        addRequirements(wheel);
    }

    @Override
    public void initialize(){
        rotateCounter = 0;
        colorCounter  = 0;
        rotated = false;
        String wantedColor = DriverStation.getInstance().getGameSpecificMessage();
        char check = wantedColor.charAt(0);
        if (check == 'B') {
            ourColor = "Red";
        } else if (check == 'R') {
            ourColor = "Blue";
        } else if (check == 'G') {
            ourColor = "Yellow";
        } else if (check == 'Y') {
            ourColor = "Green";
        } else{
            end(true);
        }
        wheel.spinWheel(wheelSpinnerSpeed);
        previousColor = wheel.getColorMatch();
    }

    @Override
    public void execute(){
        if(!rotated) {
            currentColor = wheel.getColorMatch();
            if (previousColor.equals("Red") && currentColor.equals("Yellow")) {
                rotateCounter++;
            }
            previousColor = currentColor;
        } else {
            if (ourColor.equals(wheel.getColorMatch())) {
                colorCounter++;
            }
        }
    }

    @Override
    public boolean isFinished(){
        if(rotateCounter >= 2){
            rotated = true;
        }
        if(colorCounter == 2){
            wheel.spinWheel(-wheelSpinnerSpeed);
        }
        return colorCounter >= 13;
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        wheel.spinWheel(0);
        wheel.closeServo();
    }
}
