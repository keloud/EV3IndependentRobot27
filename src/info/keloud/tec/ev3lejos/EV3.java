package info.keloud.tec.ev3lejos;

import info.keloud.tec.ev3lejos.action.*;
import lejos.hardware.Button;

import static info.keloud.tec.ev3lejos.Main.sensorUpdater;

class EV3 {
    EV3() {

    }

    void run() {
        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.setStopwatchFlag(true);

        Move move = new Move();
        MoveColor moveColor = new MoveColor();
        TurnRight turnRight = new TurnRight();
        Arm arm = new Arm();
        PIDController pidController = new PIDController();

        arm.run();
        arm.run();
        arm.run();
        arm.run();

        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.stopUpdaterFlag();
    }
}