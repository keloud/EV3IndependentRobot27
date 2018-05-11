package info.keloud.tec.ev3lejos.motor;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.TachoMotorPort;

public class CenterMotor extends EV3MediumRegulatedMotor{
    public CenterMotor(TachoMotorPort port) {
        super(port);
    }
}
