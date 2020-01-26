/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.DriveMecanum;
import frc.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.Servo;

import com.ctre.phoenix.motorcontrol.can.*;
import static frc.robot.Constants.*;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotInstance {
  // The robot's subsystems and commands are defined here...

  private RobotStick stick;
  private Drive drive;
  private VictorSPX frontLeft;
  private VictorSPX frontRight;
  private VictorSPX backLeft;
  private VictorSPX backRight;

  private VictorSPX testVictor;
  //private Test test;
  //VictorSPX followerVictor;
  Servo servo;



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotInstance() {
    stick = new RobotStick(0);
    frontLeft = new VictorSPX(frontLeftVictorID);
    frontRight = new VictorSPX(frontRightVictorID);
    backLeft = new VictorSPX(backLeftVictorID);
    backRight = new VictorSPX(backRightVictorID);
    drive = new Drive(stick, frontLeft, frontRight, backLeft, backRight);
    CommandScheduler.getInstance().setDefaultCommand(drive, new DriveMecanum(drive, stick));


    //testVictor = new VictorSPX(Constants.testVictorID);
    //test = new Test(testVictor);
    servo = new Servo(0);

  }

  public void setButtonBindings(){
    //stick.getButton(1).whileHeld(new TestCommand((test)));
  }

  public void testMotor(){
    // Motor Setting
  }

  public void testServo(){
    // Servo Setting

    double z = 85+stick.getDZ()*85; 
    servo.setAngle(z);
  }

}
