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

        // アームを空ける
        arm.run(true);
        // 真ん中のペットボトルにつっこむ
        move.run(800, 48);
        // ペットボトルをつかむ
        arm.run(true);
        // 置く場所の赤色まで進む
        moveColor.run(800, "RED", true);
        // ペットボトルを放す
        arm.run(true);
        // マップ中央まで進む
        move.run(800, -120);
        // 右のペットボトルに向く
        turn.run(800, 110);
        // ペットボトルまでつっこむ
        move.run(800, 35);
        // ペットボトルをつかむ
        arm.run(false);
        // 置く場所にゆっくり向く
        turn.run(100, -130);
        // 置く場所の赤色まで進む
        moveColor.run(800, "RED", true);
        // ペットボトルを放して下がる
        arm.run(false);
        move.run(800, -15);
        // マップ中央に向く
        turn.run(800, 15);
        // マップ中央まで進む
        move.run(800, -105);
        // 左のペットボトルに向く
        turn.run(800, -110);
        // ペットボトルまでつっこむ
        move.run(800, 35);
        // ペットボトルをつかむ
        arm.run(false);
        // 置く場所のまでゆっくり向く
        turn.run(100, 130);
        // 置く場所の赤色まで進む
        moveColor.run(800, "RED", true);
        // ペットボトルを放して下がる
        arm.run(false);
        move.run(800, -15);
        // マップ中央に向く
        turn.run(800, -15);
        // 帰宅場所の黄色まで進む
        moveColor.run(800, "YELLOW", false);

        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.stopUpdaterFlag();
    }
}