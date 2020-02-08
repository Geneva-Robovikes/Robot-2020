/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMax;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.BallIntakeMiddle;
import frc.robot.commands.DriveMecanum;
import frc.robot.commands.TestCommand;
import frc.robot.subsystems.BallSystem;
import frc.robot.subsystems.Drive;

import com.ctre.phoenix.motorcontrol.can.*;
import frc.robot.subsystems.Test;

import static frc.robot.Constants.*;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */

//GitHub Commit Test
public class RobotInstance {
  // The robot's subsystems and commands are defined here...

  private RobotStick stick;
  private Drive drive;
  private BallSystem ball;

  private WPI_VictorSPX frontLeft;
  private WPI_VictorSPX frontRight;
  private WPI_VictorSPX backLeft;
  private WPI_VictorSPX backRight;


  private CANSparkMax ballIntake;
  private Spark ballMiddle;
  private Spark ballOutput;

  private Servo servo;

  private Test test;

  private PowerDistributionPanel pdp;


  private MecanumDrive mechDrive;
  //private ADXRS450_Gyro gyro;


  private double previousGyroAngle = 0;
  private double previousTime = 0;


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotInstance() {
    pdp = new PowerDistributionPanel(0);
    DashHelper.getInstance().setUpPDPWidget(pdp);
    stick = new RobotStick(0);
    frontLeft = new WPI_VictorSPX(frontLeftVictorID);
    frontRight = new WPI_VictorSPX(frontRightVictorID);
    backLeft = new WPI_VictorSPX(backLeftVictorID);
    backRight = new WPI_VictorSPX(backRightVictorID);

    ballIntake = new CANSparkMax(sparkMAXIntake, CANSparkMaxLowLevel.MotorType.kBrushed);
    ballMiddle = new Spark(sparkMiddle);
    ballOutput = new Spark(sparkOutput);
    //servo = new Servo(2);



    //gyro = new ADXRS450_Gyro();
    mechDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

    drive = new Drive(frontLeft, frontRight, backLeft, backRight, /*gyro*/ mechDrive);

    ball = new BallSystem(ballIntake, ballMiddle, ballOutput);
    CommandScheduler.getInstance().setDefaultCommand(drive, new DriveMecanum(drive, stick));


  }

  public void setButtonBindings(){
    stick.getButton(1).whileHeld(new BallIntakeMiddle(ball));
  }

  public void testDrive(){
    //tankDrive.arcadeDrive(stick.getDY(), stick.getDZ());
  }

  /*public void servoTest() {
    if (stick.getButtonPressed(3)) {
      servo.setAngle(0);
      System.out.println(servo.getAngle());
    }
    else if (stick.getButtonPressed(4)) {
      servo.setAngle(170);
      System.out.println(servo.getAngle());
    }
  }*/
  /*public void gyroDebug(ADXRS450_Gyro gyro, Timer timer){
    if(previousTime != 0 && previousGyroAngle != 0){
      System.out.println("Gyro-reported instantaneous rate: " + gyro.getRate());
      System.out.println("Calculated instantaneous rate: " + (gyro.getAngle() - previousGyroAngle) / (timer.get() - previousTime));
    }
    previousGyroAngle = gyro.getAngle();
    previousTime = timer.get();
  }*/


}
