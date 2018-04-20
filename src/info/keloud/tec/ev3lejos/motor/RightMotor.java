package info.keloud.tec.ev3lejos.motor;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class RightMotor extends AbstractMotor {

    public RightMotor() {
        regulatedMotor = new EV3LargeRegulatedMotor(MotorPort.C);
    }
}
