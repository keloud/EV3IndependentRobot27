package info.keloud.tec.ev3lejos;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;

import static info.keloud.tec.ev3lejos.Main.leftMotor;
import static info.keloud.tec.ev3lejos.Main.rightMotor;
import static info.keloud.tec.ev3lejos.Main.sensorUpdater;

class EV3 {
    EV3() {

    }

    void run(){
        Sound.beep();
        LCD.clear(5);
        LCD.drawString("in Action", 1, 5);
        LCD.refresh();
        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.setStopwatchFlag(true);
        Sound.beep();

        //動作開始
        LCD.clear(5);
        LCD.drawString("Start Synchronization", 1, 5);
        LCD.refresh();
        LCD.clear(5);
        LCD.drawString("Set Speed", 1, 5);
        LCD.refresh();
        leftMotor.setSpeed(leftMotor.getMaxSpeed());
        rightMotor.setSpeed(rightMotor.getMaxSpeed());
        LCD.clear(5);
        LCD.drawString("Set Acceleration", 1, 5);
        LCD.refresh();
        leftMotor.setAcceleration(6000);
        rightMotor.setAcceleration(6000);
        LCD.clear(5);
        LCD.drawString("Forward", 1, 5);
        LCD.refresh();
        leftMotor.startSynchronization();
        leftMotor.forward();
        rightMotor.forward();
        leftMotor.endSynchronization();
        float cum = ((100 / 5.6F / (float) Math.PI) * 360);
        float endCum = ((leftMotor.getSpeed() / 50 / 5.6F / (float) Math.PI) * 360);
        while (leftMotor.getTachoCount() <= cum) {
            LCD.clear(6);
            LCD.drawString("Forwarding : " + leftMotor.getRotationSpeed(), 1, 6);
            LCD.refresh();
            if (leftMotor.getTachoCount() > cum - endCum) {
                float isSpeed = (leftMotor.getSpeed() - 180) * (cum - leftMotor.getTachoCount()) / endCum + 180;
                leftMotor.setSpeed(isSpeed);
                rightMotor.setSpeed(isSpeed);
            }
        }
        LCD.clear(5);
        LCD.drawString("Stop", 1, 5);
        LCD.refresh();
        leftMotor.startSynchronization();
        leftMotor.flt(true);
        rightMotor.flt(true);
        leftMotor.endSynchronization();

        LCD.clear(5);
        LCD.drawString("Complete", 1, 5);
        LCD.refresh();

        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.stopUpdaterFlag();
    }
}
