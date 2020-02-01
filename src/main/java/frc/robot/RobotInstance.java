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
  private CANSparkMax testSparkMAX;

  private VictorSPX testVictor;

  private MecanumDrive mechDrive;
  private Test test;
  //VictorSPX followerVictor;
  Servo servo;



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotInstance() {
    stick = new RobotStick(0);
    frontLeft = new WPI_VictorSPX(frontLeftVictorID);
    frontRight = new WPI_VictorSPX(frontRightVictorID);
    backLeft = new WPI_VictorSPX(backLeftVictorID);
    backRight = new WPI_VictorSPX(backRightVictorID);
    testSparkMAX = new CANSparkMax(testSparkMAXOne, CANSparkMaxLowLevel.MotorType.kBrushed);
    drive = new Drive(frontLeft, frontRight, backLeft, backRight);
    CommandScheduler.getInstance().setDefaultCommand(drive, new DriveMecanum(drive, stick));
    //mechDrive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);


    //testVictor = new VictorSPX(Constants.testVictorID);
    test = new Test(testSparkMAX);
    servo = new Servo(0);

  }

  public void setButtonBindings(){
    stick.getButton(1).whileHeld(new TestCommand((test)));
  }

  public void testDrive(){
    mechDrive.driveCartesian(stick.getDY(), stick.getDX(), stick.getDZ());
  }

  public void testServo(){
    // Servo Setting

    double z = 85+stick.getDZ()*85; 
    servo.setAngle(z);
  }

}
