package frc.robot.commands.wheelcommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WheelSpinner;

import static frc.robot.Constants.*;

public class SpinWheelColor extends CommandBase {
    private WheelSpinner wheel;
    private String ourColor;
    private int counter;

    public SpinWheelColor(WheelSpinner wheel){
        this.wheel = wheel;
        addRequirements(wheel);
    }

    @Override
    public void initialize(){
        String wantedColor = DriverStation.getInstance().getGameSpecificMessage();
        wheel.spinWheel(wheelSpinnerSpeed);
        char check = wantedColor.charAt(0);
        if (check == 'B') {
            ourColor = "Red";
        } else if (check == 'R') {
            ourColor = "Blue";
        } else if (check == 'G') {
            ourColor = "Yellow";
        } else if (check == 'Y') {
            ourColor = "Green";
        }
    }

    @Override
    public void execute(){
        // See color for .1 seconds before stopping
        if(ourColor.equals(wheel.getColorMatch())){
            counter++;
        }
    }

    @Override
    public boolean isFinished(){
        return counter >= 5;
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        wheel.spinWheel(0);
        wheel.closeServo();
    }
}
