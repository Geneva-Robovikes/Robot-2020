package frc.robot.commands.wheelcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WheelSpinner;

import static frc.robot.Constants.*;

public class SpinWheel extends CommandBase {
    private WheelSpinner wheelSpinner;
    public SpinWheel(WheelSpinner spinner){
        this.wheelSpinner = spinner;
        addRequirements(wheelSpinner);
    }

    @Override
    public void initialize(){
        wheelSpinner.spinWheel(wheelSpinnerSpeed);
    }

    @Override
    public void execute(){

    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        wheelSpinner.spinWheel(0);
    }

}
