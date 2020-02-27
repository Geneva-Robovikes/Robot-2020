package frc.robot.commands.autocommandgroups;

import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivecommands.DriveForward;
import frc.robot.commands.drivecommands.DriveMecanum;
import frc.robot.commands.drivecommands.EmergencyStop;
import frc.robot.commands.drivecommands.SpinAngle;
import frc.robot.subsystems.Drive;

public class BasicAuto extends SequentialCommandGroup {

    public BasicAuto (Drive drive, double angle) {
        addCommands(
                new DriveForward(drive, .5, 3),
                new SpinAngle(drive, angle),
                new DriveForward(drive, .5, 3)
        );
    }

}
