/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.ballcommands.*;
import frc.robot.commands.drivecommands.DriveMecanum;
import frc.robot.commands.drivecommands.EmergencyStop;
import frc.robot.commands.drivecommands.SpinAngle;
import frc.robot.subsystems.BallSystem;
import frc.robot.subsystems.Drive;
import com.analog.adis16448.frc.ADIS16448_IMU;

import com.ctre.phoenix.motorcontrol.can.*;
import frc.robot.subsystems.WheelSpinner;

import static frc.robot.Constants.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */

public class RobotInstance {
  // Declare all the variables here

  // Not Subsystem-Specific
  private RobotStick stick;
  private static PowerDistributionPanel pdp;


  // Drive Components + Subsystem
  private WPI_VictorSPX frontLeft;
  private WPI_VictorSPX frontRight;
  private WPI_VictorSPX backLeft;
  private WPI_VictorSPX backRight;

  private ADIS16448_IMU gyro;
  private MecanumDrive mechDrive;

  private Drive drive;




  // Ball Components + Subsystem
  private CANSparkMax ballFlywheel1;
  private CANSparkMax ballFlywheel2;
  private Spark ballIntake;
  private Spark ballMiddle;
  private Servo servo;
  private BallSystem ball;

  private ColorSensorV3 colorSensor;
  private WheelSpinner wheelSpinner;

  public RobotInstance() {
    // Not Subsystem-Specific
    stick = new RobotStick(0);
    pdp = new PowerDistributionPanel(0);
    pdp.clearStickyFaults();
    DashHelper.getInstance().setUpPDPWidget(pdp);

    // Drive Components + Subsystem
    frontLeft = new WPI_VictorSPX(frontLeftVictorID);
    frontRight = new WPI_VictorSPX(frontRightVictorID);
    backLeft = new WPI_VictorSPX(backLeftVictorID);
    backRight = new WPI_VictorSPX(backRightVictorID);

    gyro = new ADIS16448_IMU();
    mechDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

    drive = new Drive(frontLeft, frontRight, backLeft, backRight, gyro, mechDrive);

    // Ball Components + Subsystem
    ballIntake = new Spark(sparkIntake);
    ballMiddle = new Spark(sparkMiddle);
    ballFlywheel1 = new CANSparkMax(sparkMAXFlywheel1, CANSparkMaxLowLevel.MotorType.kBrushed);
    ballFlywheel2 = new CANSparkMax(sparkMAXFlywheel2, CANSparkMaxLowLevel.MotorType.kBrushed);
    servo = new Servo(servoPort);

    ball = new BallSystem(ballIntake, ballMiddle, ballFlywheel1, ballFlywheel2,  servo);

    // Wheel Spinner Components + Subsystem
    colorSensor = new ColorSensorV3(i2cPort);
    wheelSpinner = new WheelSpinner(ballFlywheel2, colorSensor);



  }

  public void initAutoCommands(){
    // Kill anything just in case
    CommandScheduler.getInstance().cancelAll();
  }

  public void initTeleopCommands(){
    // Kill anything left over from auto
    CommandScheduler.getInstance().cancelAll();

    // Defaults
    CommandScheduler.getInstance().setDefaultCommand(drive, new DriveMecanum(drive, stick));

    // Button bindings
    // Drive
    //stick.getButton(spin180Button).whenPressed(new SpinAngle(drive, 180));
    stick.getButton(emergencyStopButton).whenPressed(new EmergencyStop(drive));


    // Ball
    stick.getButton(ballFlywheelButton).whileHeld(new BallFlywheel(ball));
    stick.getButton(ballMiddleIntakeButton).whileHeld(new BallIntakeMiddle(ball));
    stick.getButton(flipServoButton).whenPressed(new FlipServo(ball));
    stick.getButton(reverseIntakeButton).whileHeld(new ReverseIntake(ball));
    stick.getButton(reverseMiddleButton).whileHeld(new ReverseMiddle(ball));
    stick.getButton(5).whileHeld(new DumpEmOut(ball));



    //stick.getButton(4).whenPressed(new SpinAngle(drive, -45));

  }

  public static PowerDistributionPanel getPDP(){
    return pdp;
  }

  public void resetGyro(){
    drive.resetGyro();
  }



}
