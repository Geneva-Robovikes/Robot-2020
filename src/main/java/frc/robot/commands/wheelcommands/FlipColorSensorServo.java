package frc.robot.commands.wheelcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.DashHelper;
import frc.robot.subsystems.BallSystem;
import frc.robot.subsystems.WheelSpinner;

public class FlipColorSensorServo extends CommandBase {
    private WheelSpinner wheel;

    public FlipColorSensorServo(WheelSpinner wheel){
        this.wheel = wheel;
    }

    @Override
    public void initialize(){
        if(wheel.getServoAngle() == 0){
            wheel.openServo();
            //DashHelper.sbServoOpen.setBoolean(true);
        } else {
            wheel.closeServo();
            //DashHelper.sbServoOpen.setBoolean(false);
        }
    }

    @Override
    public boolean isFinished(){
        return true;
    }


}
