package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;;


public class RobotStick extends Joystick { // Defines the joystick
    double dead;
    public JoystickButton buttons[];



    public RobotStick(int port) { //The states of all the buttons on the joystick in an array
        super(port);

        buttons = new JoystickButton[12];

        for(int i = 0; i < buttons.length; i++) {
            buttons[i] = new JoystickButton(this, i+1);
        }

    }

    public double deadZone(double input, double dead) { // Deadzone for all joystick input

        if (input < dead && input > -dead) {
            return 0;
        }
        else {
            return input;
        }
    }

    public double getDX() { // Gets joystick X input (left/right)
        return deadZone(this.getRawAxis(0), .20);
    }

    public double getDY() { // Gets joystick Y input (forward/backward)
        // Negative because Y-Axis is inverted
        return -1 * deadZone(this.getRawAxis(1), .20);
    }

    public double getDZ() { // Gets joystick Z input (twist)
        return deadZone(this.getRawAxis(2), .2);
    }

    public int getNub() { // Input for POV stick
        return this.getPOV();
    }

    public JoystickButton getButton(int button) { // Continuous input while button is pressed
        return this.buttons[button-1];
    }


    public double getStickDegree() { // Returns the angle that the joystick is at in degrees
        return Math.toDegrees(Math.asin(getDY()/(Math.pow((Math.pow(getDX(),2)+Math.pow(getDY(),2)),0.5))));
    }
}