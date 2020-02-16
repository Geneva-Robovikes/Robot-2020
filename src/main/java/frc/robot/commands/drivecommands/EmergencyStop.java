package frc.robot.commands.drivecommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class EmergencyStop extends CommandBase {

    private Drive drive;
    public EmergencyStop(Drive drive){
        this.drive = drive;
        addRequirements(drive);
    }

    @Override
    public void initialize(){
        drive.setMechDriveManual(0, 0, 0);
        drive.setDriveVictors(0, 0,0, 0);
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
