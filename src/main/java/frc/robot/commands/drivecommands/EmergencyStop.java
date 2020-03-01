package frc.robot.commands.drivecommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotInstance;
import frc.robot.subsystems.BallSystem;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.WheelSpinner;

public class EmergencyStop extends CommandBase {
    private Timer t;
    private Drive drive;
    private BallSystem ball;
    private Lift lift;
    private WheelSpinner wheel;
    public EmergencyStop(Drive drive, BallSystem ball, Lift lift, WheelSpinner wheel){
        this.drive = drive;
        this.ball = ball;
        this.lift = lift;
        this.wheel = wheel;
        t = new Timer();
        // Interrupts anything that is happening on all subsystems
        addRequirements(drive, ball, lift, wheel);
    }

    @Override
    public void initialize(){
        t.start();
        // Drive stop + reset
        drive.setMechDriveManual(0, 0, 0);
        drive.setDriveVictors(0, 0,0, 0);
        drive.resetGyro();

        // Ball system stop
        ball.spinFlywheel(0);
        ball.spinIntakeMiddle(0, 0);

        // Lift stop
        lift.setLift(0);

        // Wheel stop
        wheel.spinWheel(0);

        RobotInstance.getPDP().clearStickyFaults();
    }

    @Override
    public void execute(){
        // Keep updating the mecanum drive object so it doesn't error out
        drive.setMechDriveManual(0, 0, 0);
    }

    @Override
    public boolean isFinished(){
        // Hold the pause for 2 seconds
        return t.get() > 2;
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        t.stop();
    }

}
