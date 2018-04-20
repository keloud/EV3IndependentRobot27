package info.keloud.tec.ev3lejos.motor;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.robotics.RegulatedMotor;

public class AbstractMotor {
    public BaseRegulatedMotor regulatedMotor;

    public void forward() {
        regulatedMotor.forward();
    }

    public void backward() {
        regulatedMotor.backward();
    }

    public void stop() {
        regulatedMotor.stop();
    }

    public void stop(boolean immediateReturn) {
        regulatedMotor.stop(immediateReturn);
    }

    public void flt() {
        regulatedMotor.flt();
    }

    public int getTachoCount() {
        return regulatedMotor.getTachoCount();
    }

    public float getMaxSpeed() {
        return regulatedMotor.getMaxSpeed();
    }

    public void setAcceleration(int acceleration) {
        regulatedMotor.setAcceleration(acceleration);
    }

    public void setSpeed(float speed) {
        regulatedMotor.setSpeed(speed);
    }

    public void synchronizeWith(RegulatedMotor[] syncList) {
        regulatedMotor.synchronizeWith(syncList);
    }

    public void startSynchronization() {
        regulatedMotor.startSynchronization();
    }
}
