package frc.robot.commands.autocommandgroups;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ballcommands.DumpEmOut;
import frc.robot.commands.drivecommands.DriveForward;
import frc.robot.subsystems.BallSystem;
import frc.robot.subsystems.Drive;

public class ScoreAuto  extends SequentialCommandGroup {
    private Drive drive;
    private BallSystem ball;

    public ScoreAuto(Drive drive, BallSystem ball){
        this.drive = drive;
        this.ball = ball;

        addCommands(
                new DriveForward(drive, .5, 5),
                new DumpEmOut(ball)
        );
    }
}
