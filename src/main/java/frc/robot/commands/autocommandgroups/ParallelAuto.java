package frc.robot.commands.autocommandgroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.ballcommands.BallIntakeMiddle;
import frc.robot.commands.drivecommands.DriveForward;
import frc.robot.subsystems.BallSystem;
import frc.robot.subsystems.Drive;

public class ParallelAuto extends ParallelCommandGroup {

    public ParallelAuto(Drive drive, BallSystem ballSubsystem) {
        addCommands(
                new BallIntakeMiddle(ballSubsystem),
                new DriveForward(drive, .5, 3)
        );
    }
}
