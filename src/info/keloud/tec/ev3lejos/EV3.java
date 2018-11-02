package info.keloud.tec.ev3lejos;

import info.keloud.tec.ev3lejos.action.Forward;
import info.keloud.tec.ev3lejos.action.TurnRight;
import lejos.hardware.Button;

import static info.keloud.tec.ev3lejos.Main.sensorUpdater;

class EV3 {
    EV3() {

    }

    void run() {
        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.setStopwatchFlag(true);

        Forward forward = new Forward();
        TurnRight turnRight = new TurnRight();

        forward.run(500, 50);
        turnRight.run(500, 180);
        forward.run(500, 50);
        turnRight.run(500, 180);

        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.stopUpdaterFlag();
    }
}