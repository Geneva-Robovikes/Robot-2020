package frc.robot.commands.drivecommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotInstance;
import frc.robot.subsystems.Drive;

public class EmergencyStop extends CommandBase {
    private Timer t;
    private Drive drive;
    public EmergencyStop(Drive drive){
        this.drive = drive;
        t = new Timer();
        addRequirements(drive);
    }

    @Override
    public void initialize(){
        t.start();
        drive.setMechDriveManual(0, 0, 0);
        drive.setDriveVictors(0, 0,0, 0);
        drive.resetGyro();
        RobotInstance.getPDP().clearStickyFaults();
    }

    @Override
    public boolean isFinished(){
        return t.get() > 2;
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        t.stop();
    }

}
