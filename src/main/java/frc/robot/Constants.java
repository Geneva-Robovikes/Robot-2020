/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

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
    public static final int frontLeftVictorID = 4;
    public static final int frontRightVictorID = 3;
    public static final int backLeftVictorID = 1;
    public static final int backRightVictorID = 2;

    // Speeds
    public static final double xSpeed = .80;
    public static final double ySpeed = 1;
    public static final double zSpeedAuto = .45;
    public static final double zSpeedManual = .85;

    // Deadzones
    public static final double xDeadZone = .20;
    public static final double yDeadZone = .10;
    public static final double zDeadZone = .30;
    public static final double gyroDeadZone = 1;

    // Button Bindings
    public static final int spin180Button = 3;
    public static final int emergencyStopButton = 12;

    // Other
    public static final double cutOffVoltage = 11.75;


    // Ball System
    // Motor Controllers
    public static final int sparkIntakePort = 1;
    public static final int sparkMiddlePort = 0;
    public static final int sparkMAXFlywheel1ID = 5;
    public static final int sparkMAXFlywheel2ID = 6;

    // Servo
    public static final int servoPort = 2;

    // Speeds
    public static final double intakeSpeed = .4;
    public static final double middleSpeed = -.65;
    public static final double flywheelSpeed = -.50;

    // Button Bindings
    public static final int ballFlywheelButton = 1;
    public static final int ballMiddleIntakeButton = 2;
    public static final int reverseIntakeButton = 4;
    public static final int flipServoButton = 5;
    public static final int reverseMiddleButton = 6;
    public static final int dumpEmOutButton = 7;

    // Lift
    // Motor Controllers
    public static final int talonLift1Port = 3;
    public static final int talonLift2Port = 4;

    // Speeds
    public static final double liftSpeed = .5;

    // Button Bindings
    public static final int liftUpButton = 10;
    public static final int liftDownButton = 11;

    // Wheel Spinner
    public static final I2C.Port i2cPort = I2C.Port.kOnboard;
    public static final double wheelSpinnerSpeed = 0.25;

    // Colors
    public static final Color blueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    public static final Color greenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    public static final Color redTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    public static final Color yellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);


    // Shuffleboard
    public static final double kSpeed = .5;
                                                                                                                                                                                                                                                                                                                                              

}
