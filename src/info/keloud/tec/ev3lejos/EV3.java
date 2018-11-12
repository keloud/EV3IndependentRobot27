package info.keloud.tec.ev3lejos;

import info.keloud.tec.ev3lejos.action.*;
import lejos.hardware.Button;

import static info.keloud.tec.ev3lejos.Main.*;

class EV3 {
    EV3() {

    }

    void run() {
        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.setStopwatchFlag(true);

        leftMotor.resetTachoCount();
        rightMotor.resetTachoCount();
        centerMotor.resetTachoCount();
        gyroSensor.initGyro();

        Move move = new Move();
        MoveColor moveColor = new MoveColor();
        Turn turn = new Turn();
        Arm arm = new Arm();
        PIDController pidController = new PIDController();

        arm.run(true);
        move.run(800, 48);
        arm.run();
        moveColor.run(800, "RED", true);
        arm.run();
        move.run(800, -120);
        turn.run(800, 110);
        move.run(800, 50);
        arm.run();
        turn.run(800, -150);
        moveColor.run(800, "RED", true);
        arm.run();
        move.run(800, -20);


        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.stopUpdaterFlag();
    }
}