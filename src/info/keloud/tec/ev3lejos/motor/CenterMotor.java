package info.keloud.tec.ev3lejos.motor;

import info.keloud.tec.ev3lejos.motor.AbstractMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class CenterMotor extends AbstractMotor {
    public CenterMotor() {
        regulatedMotor = new EV3MediumRegulatedMotor(MotorPort.A);
    }
}
