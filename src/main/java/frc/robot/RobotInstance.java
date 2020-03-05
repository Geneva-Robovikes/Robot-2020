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
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.autocommandgroups.ScoreAuto;
import frc.robot.commands.ballcommands.*;
import frc.robot.commands.drivecommands.DriveMecanum;
import frc.robot.commands.drivecommands.EmergencyStop;
import frc.robot.commands.drivecommands.SpinAngle;
import frc.robot.commands.liftcommands.PowerLeftLift;
import frc.robot.commands.liftcommands.PowerRightLift;
import frc.robot.commands.liftcommands.UnwindLeftLift;
import frc.robot.commands.liftcommands.UnwindRightLift;
import frc.robot.commands.wheelcommands.SpinWheel;
import frc.robot.subsystems.BallSystem;
import frc.robot.subsystems.Drive;
import com.analog.adis16448.frc.ADIS16448_IMU;

import com.ctre.phoenix.motorcontrol.can.*;
import frc.robot.subsystems.Lift;
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
  private boolean liftoMode = false;

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
  private Servo ballServo;
  private BallSystem ball;

  // Wheel Components +  Subsystem
  private ColorSensorV3 colorSensor;
  private WheelSpinner wheelSpinner;
  private Servo colorSensorServo;

  // Lift Components + Subsystem
  private Talon liftTalonLeft;
  private Talon liftTalonRight;
  private Lift lift;

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
    ballIntake = new Spark(sparkIntakePort);
    ballMiddle = new Spark(sparkMiddlePort);
    ballFlywheel1 = new CANSparkMax(sparkMAXFlywheel1ID, CANSparkMaxLowLevel.MotorType.kBrushed);
    ballFlywheel2 = new CANSparkMax(sparkMAXFlywheel2ID, CANSparkMaxLowLevel.MotorType.kBrushed);
    ballServo = new Servo(ballServoPort);

    ball = new BallSystem(ballIntake, ballMiddle, ballFlywheel1, ballFlywheel2, ballServo);

    // Wheel Spinner Components + Subsystem
    colorSensor = new ColorSensorV3(i2cPort);
    colorSensorServo = new Servo(colorSensorServoPort);
    wheelSpinner = new WheelSpinner(ballFlywheel2, colorSensor, colorSensorServo);

    // Lift Components + Subsystem
    liftTalonLeft = new Talon(talonLiftLeftPort);
    liftTalonRight = new Talon(talonLiftRightPort);
    lift = new Lift(liftTalonLeft, liftTalonRight);

  }

  public void initAutoCommands(){
    // Kill anything just in case
    CommandScheduler.getInstance().cancelAll();

    //BasicAuto bAuto = new BasicAuto(drive, 90);
    ScoreAuto sAuto = new ScoreAuto(drive, ball);
    CommandScheduler.getInstance().schedule(sAuto);
  }

  public void initTeleopCommands(){
    // Kill anything left over from auto
    CommandScheduler.getInstance().cancelAll();

    // Defaults
    CommandScheduler.getInstance().setDefaultCommand(drive, new DriveMecanum(drive, stick));

    // Button bindings
    // Drive
    stick.getButton(spin180Button).whenPressed(new SpinAngle(drive, 180));
    stick.getButton(emergencyStopButton).whenPressed(new EmergencyStop(drive, ball, lift, wheelSpinner), false);

    // Ball
    stick.getButton(ballFlywheelButton).whileHeld(new BallFlywheel(ball));
    stick.getButton(ballMiddleIntakeButton).whileHeld(new BallIntakeMiddle(ball));
    stick.getButton(flipServoButton).whenPressed(new FlipServo(ball));
    stick.getButton(reverseIntakeButton).whileHeld(new ReverseIntake(ball));
    stick.getButton(reverseMiddleButton).whileHeld(new ReverseMiddle(ball));
    stick.getButton(dumpEmOutButton).whenPressed(new DumpEmOut(ball));

    // Wheel
    stick.getButton(wheelSpinnerButton).whenPressed(new SpinWheel(wheelSpinner));

    // Lift
    stick.getButton(leftLiftPowerButton).whileHeld(new PowerLeftLift(lift, stick));
    stick.getButton(rightLiftPowerButton).whileHeld(new PowerRightLift(lift,  stick));

    //stick.getButton(leftLiftUnwindButton).whileHeld(new UnwindLeftLift(lift, stick));
    //stick.getButton(rightLiftUnwindButton).whileHeld(new UnwindRightLift(lift, stick));


  }



  public void testSlider(){
    System.out.println(stick.getSlider());
  }

  /*public void liftoMode(){
    if(stick.getButton(11).get()) {
      liftoMode = !liftoMode;
      if(liftoMode){
        // Rebind buttons

      } else{
        initTeleopCommands();
      }
    }

  }*/

  public static PowerDistributionPanel getPDP(){
    return pdp;
  }

  public void resetGyro(){
    drive.resetGyro();
  }



}
