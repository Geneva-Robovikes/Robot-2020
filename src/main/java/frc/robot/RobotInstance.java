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
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.DriveMecanum;
import frc.robot.commands.TestCommand;
import frc.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.Servo;

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
  private WPI_VictorSPX frontLeft;
  private WPI_VictorSPX frontRight;
  private WPI_VictorSPX backLeft;
  private WPI_VictorSPX backRight;

  private CANSparkMax spark;
  private Test test;


  private DifferentialDrive tankDrive;
  private MecanumDrive mechDrive;
  //VictorSPX followerVictor;
  Servo servo;


  private double previousGyroAngle = 0;
  private double previousTime = 0;


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotInstance() {

    stick = new RobotStick(0);
    frontLeft = new WPI_VictorSPX(frontLeftVictorID);
    frontRight = new WPI_VictorSPX(frontRightVictorID);
    backLeft = new WPI_VictorSPX(backLeftVictorID);
    backRight = new WPI_VictorSPX(backRightVictorID);
    //drive = new Drive(frontLeft, frontRight, backLeft, backRight);
    //CommandScheduler.getInstance().setDefaultCommand(drive, new DriveMecanum(drive, stick));

    /*SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, backLeft);
    SpeedControllerGroup right = new SpeedControllerGroup(frontRight, backRight);
    tankDrive = new DifferentialDrive(left, right);*/

    mechDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);


    //testVictor = new VictorSPX(Constants.testVictorID);
    servo = new Servo(0);

    spark = new CANSparkMax(testSparkMAXOne, CANSparkMaxLowLevel.MotorType.kBrushed);
    test = new Test(spark);

  }

  public void setButtonBindings(){
    stick.getButton(1).whileHeld(new TestCommand((test)));
  }

  public void testDrive(){
    mechDrive.driveCartesian(xSpeed * stick.getDX(), ySpeed * stick.getDY(), turnSpeed * stick.getDZ());
    //tankDrive.arcadeDrive(stick.getDY(), stick.getDZ());
  }

  public void gyroDebug(ADXRS450_Gyro gyro, Timer timer){
    if(previousTime != 0 && previousGyroAngle != 0){
      System.out.println("Gyro-reported instantaneous rate: " + gyro.getRate());
      System.out.println("Calculated instantaneous rate: " + (gyro.getAngle() - previousGyroAngle) / (timer.get() - previousTime));
    }
    previousGyroAngle = gyro.getAngle();
    previousTime = timer.get();
  }

  public void testServo(){
    // Servo Setting

    double z = 85+stick.getDZ()*85; 
    servo.setAngle(z);
  }

}
