package info.keloud.tec.ev3lejos.motor;

import lejos.hardware.motor.MotorRegulator;
import lejos.hardware.port.TachoMotorPort;

public class RightTachoMotorPort implements TachoMotorPort {
    @Override
    public MotorRegulator getRegulator() {
        return null;
    }

    @Override
    public void controlMotor(int i, int i1) {

    }

    @Override
    public void setPWMMode(int i) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean setPinMode(int i) {
        return false;
    }

    @Override
    public int getTachoCount() {
        return 0;
    }

    @Override
    public void resetTachoCount() {

    }
}
