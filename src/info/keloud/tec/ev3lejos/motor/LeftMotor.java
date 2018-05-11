package info.keloud.tec.ev3lejos.motor;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.TachoMotorPort;

public class LeftMotor extends EV3LargeRegulatedMotor{
    public LeftMotor(TachoMotorPort port) {
        super(port);
    }
}
