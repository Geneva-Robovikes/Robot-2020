/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.ballcommands.BallFlywheel;
import frc.robot.commands.ballcommands.BallIntakeMiddle;
import frc.robot.commands.ballcommands.FlipServo;
import frc.robot.commands.drivecommands.DriveMecanum;
import frc.robot.commands.drivecommands.SpinAngle;
import frc.robot.subsystems.BallSystem;
import frc.robot.subsystems.Drive;
import com.analog.adis16448.frc.ADIS16448_IMU;

import com.ctre.phoenix.motorcontrol.can.*;

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
  private PowerDistributionPanel pdp;


  // Drive Components + Subsystem
  private WPI_VictorSPX frontLeft;
  private WPI_VictorSPX frontRight;
  private WPI_VictorSPX backLeft;
  private WPI_VictorSPX backRight;

  private ADIS16448_IMU gyro;
  private MecanumDrive mechDrive;

  private Drive drive;




  // Ball Components + Subsystem
  private CANSparkMax ballIntake;
  private CANSparkMax ballMiddle;
  private  Spark ballOutput;

  private BallSystem ball;

  private Servo servo;

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
    ballIntake = new CANSparkMax(sparkMAXIntake, CANSparkMaxLowLevel.MotorType.kBrushed);
    ballMiddle = new CANSparkMax(sparkMiddle, CANSparkMaxLowLevel.MotorType.kBrushed);
    ballOutput = new Spark(sparkOutput);
    servo = new Servo(servoPort);

    ball = new BallSystem(ballIntake, ballMiddle, ballOutput, servo);



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
    stick.getButton(1).whileHeld(new BallFlywheel(ball));
    stick.getButton(2).whileHeld(new BallIntakeMiddle(ball));
    stick.getButton(3).whenPressed(new SpinAngle(drive, 180));
    stick.getButton(4).whenPressed(new SpinAngle(drive, -45));
    stick.getButton(5).whenPressed(new FlipServo(ball));
  }




}
