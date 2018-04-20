package info.keloud.tec.ev3lejos.motor;

import info.keloud.tec.ev3lejos.motor.AbstractMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class LeftMotor extends AbstractMotor {
    public LeftMotor() {
        regulatedMotor = new EV3LargeRegulatedMotor(MotorPort.B);
    }
}
