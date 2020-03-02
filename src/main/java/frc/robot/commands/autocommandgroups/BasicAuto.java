package frc.robot.commands.autocommandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivecommands.DriveForwardTimed;
import frc.robot.commands.drivecommands.SpinAngle;
import frc.robot.subsystems.Drive;

public class BasicAuto extends SequentialCommandGroup {

    public BasicAuto (Drive drive, double angle) {
        addCommands(
                new DriveForwardTimed(drive, .5, 3),
                new SpinAngle(drive, angle),
                new DriveForwardTimed(drive, .5, 3)
        );
    }

}
