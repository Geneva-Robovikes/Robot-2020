package frc.robot.commands.wheelcommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WheelSpinner;

import static frc.robot.Constants.*;

public class SpinWheelColor extends CommandBase {
    private WheelSpinner wheel;
    private String ourColor;
    private String wantedColor;
    public SpinWheelColor(WheelSpinner wheel){
        this.wheel = wheel;
        addRequirements(wheel);
    }

    @Override
    public void initialize(){
        this.wantedColor = DriverStation.getInstance().getGameSpecificMessage();
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
        System.out.println("Here");
        System.out.println(ourColor);
    }

    @Override
    public void execute(){

    }

    @Override
    public boolean isFinished(){
        if (ourColor == wheel.getColorMatch()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        wheel.spinWheel(0);
    }
}
