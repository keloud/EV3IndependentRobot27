package info.keloud.tec.ev3lejos;

import info.keloud.tec.ev3lejos.action.Arm;
import info.keloud.tec.ev3lejos.action.Move;
import info.keloud.tec.ev3lejos.action.MoveColor;
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

        Move move = new Move();
        MoveColor moveColor = new MoveColor();
        TurnRight turnRight = new TurnRight();
        Arm arm = new Arm();

        arm.run();
        move.run(800, 40);
        arm.run();
        moveColor.run(800, true, "RED");
        arm.run();
        moveColor.run(800, false, "YELLOW");


        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.stopUpdaterFlag();
    }
}