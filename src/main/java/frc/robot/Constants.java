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
    public static final double gyroDeadZone = 1;

    // Button Bindings
    public static final int spin180Button = 3;
    public static final int emergencyStopButton = 12;


    // Ball System
    // Motor Controllers
    public static final int sparkIntake = 1;
    public static final int sparkMiddle = 0;
    public static final int sparkMAXFlywheel1 = 5;
    public static final int sparkMAXFlywheel2 = 6;

    // Servo
    public static final int servoPort = 2;

    // Speeds
    public static final double intakeSpeed = 0;
    public static final double middleSpeed = -0;
    public static final double flywheelSpeed = .50;

    // Button Bindings
    public static final int ballFlywheelButton = 1;
    public static final int ballMiddleIntakeButton = 2;
    public static final int flipServoButton = 5;
    public static final int reverseIntakeButton = 4;
    public static final int reverseMiddleButton = 6;

    // Climb



    // Shuffleboard
    public static final double kSpeed = .5;
                                                                                                                                                                                                                                                                                                                                              

}
