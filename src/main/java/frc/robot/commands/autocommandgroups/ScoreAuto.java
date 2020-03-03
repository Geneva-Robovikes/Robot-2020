package frc.robot.commands.autocommandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ballcommands.DumpEmOut;
import frc.robot.commands.drivecommands.DriveForwardTimed;
import frc.robot.commands.drivecommands.SpinAngle;
import frc.robot.subsystems.BallSystem;
import frc.robot.subsystems.Drive;

public class ScoreAuto  extends SequentialCommandGroup {
    private Drive drive;
    private BallSystem ball;

    public ScoreAuto(Drive drive, BallSystem ball){
        this.drive = drive;
        this.ball = ball;

        addCommands(
                new DriveForwardTimed(drive, -.35, 2.25),
                new DumpEmOut(ball),
                new DriveForwardTimed(drive, .25, 1),
                new SpinAngle(drive, -90),
                new DriveForwardTimed(drive, .25, 2)


        );
    }
}
