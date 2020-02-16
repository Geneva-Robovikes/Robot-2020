/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

public final class Constants {
    //Drive
    // Motor Controllers
    public static final int frontLeftVictorID = 2;
    public static final int frontRightVictorID = 1;
    public static final int backLeftVictorID = 3;
    public static final int backRightVictorID = 4;

    // Speeds
    public static final double xSpeed = .60;
    public static final double ySpeed = 1;
    public static final double zSpeedAuto = .45;
    public static final double zSpeedManual = .75;

    // Deadzones
    public static final double xDeadZone = .10;
    public static final double yDeadZone = .10;
    public static final double zDeadZone = .30;
    public static final double gyroDeadZone = 0.5;


    // Ball System
    // Motor Controllers
    public static final int sparkMAXIntake = 5;
    public static final int sparkMiddle = 6;
    public static final int sparkFlywheel1 = 0;
    public static final int sparkFlywheel2 = 1;

    // Servo
    public static final int servoPort = 2;

    // Speeds
    public static final double intakeSpeed = 1;
    public static final double middleSpeed = 1;
    public static final double flywheelSpeed = 1;


    // Climb



    // Shuffleboard
    public static final double kSpeed = .5;
                                                                                                                                                                                                                                                                                                                                              

}
