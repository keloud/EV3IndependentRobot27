package info.keloud.tec.ev3lejos;

import info.keloud.tec.ev3lejos.action.Forward;
import lejos.hardware.Button;

import static info.keloud.tec.ev3lejos.Main.sensorUpdater;

class EV3 {
    EV3() {

    }

    void run() {
        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.setStopwatchFlag(true);

        new Forward().run(500, 100);

        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.stopUpdaterFlag();
    }
}