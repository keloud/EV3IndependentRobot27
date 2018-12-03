package info.keloud.tec.ev3lejos;

import info.keloud.tec.ev3lejos.action.Arm;
import info.keloud.tec.ev3lejos.action.Move;
import info.keloud.tec.ev3lejos.action.MoveColor;
import info.keloud.tec.ev3lejos.action.Turn;
import lejos.hardware.Button;

import static info.keloud.tec.ev3lejos.Main.*;

class EV3 {
    EV3() {

    }

    void run() {
        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.setStopwatchFlag(true);

        go2();

        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.stopUpdaterFlag();
    }

    private void go1() {
        leftMotor.resetTachoCount();
        rightMotor.resetTachoCount();
        centerMotor.resetTachoCount();
        gyroSensor.initGyro();

        Move move = new Move();
        MoveColor moveColor = new MoveColor();
        Turn turn = new Turn();
        Arm arm = new Arm();

        float maxSpeed = 800;

        // アームを空ける
        arm.run(true);
        // 真ん中のペットボトルにつっこむ
        move.run(maxSpeed, 48);
        // ペットボトルをつかむ
        arm.run(true);
        // 置く場所の赤色まで進む
        moveColor.run(maxSpeed, "RED", true);
        // ペットボトルを放す
        arm.run(true);
        // マップ中央まで進む
        move.run(maxSpeed, -120);
        // 右のペットボトルに向く
        turn.run(maxSpeed, 110);
        // ペットボトルまでつっこむ
        move.run(maxSpeed, 35);
        // ペットボトルをつかむ
        arm.run(false);
        // 置く場所にゆっくり向く
        turn.run(100, -130);
        // 置く場所の赤色まで進む
        moveColor.run(maxSpeed, "RED", true);
        // ペットボトルを放して下がる
        arm.run(false);
        move.run(maxSpeed, -15);
        // マップ中央に向く
        turn.run(maxSpeed, 10);
        // マップ中央まで進む
        move.run(maxSpeed, -105);
        // 左のペットボトルに向く
        turn.run(maxSpeed, -110);
        // ペットボトルまでつっこむ
        move.run(maxSpeed, 35);
        // ペットボトルをつかむ
        arm.run(false);
        // 置く場所のまでゆっくり向く
        turn.run(100, 130);
        // 置く場所の赤色まで進む
        moveColor.run(maxSpeed, "RED", true);
        // ペットボトルを放して下がる
        arm.run(false);
        move.run(maxSpeed, -15);
        // マップ中央に向く
        turn.run(maxSpeed, -15);
        // 帰宅場所の黄色まで進む
        moveColor.run(maxSpeed, "YELLOW", false);
    }

    private void go2() {
        leftMotor.resetTachoCount();
        rightMotor.resetTachoCount();
        centerMotor.resetTachoCount();
        gyroSensor.initGyro();

        float maxSpeed = 800;

        // アームを空ける
        new Arm().run(true);
        // 真ん中のペットボトルにつっこむ
        new Move().run(maxSpeed, 48);
        // ペットボトルをつかむ
        new Arm().run(true);
        // 置く場所の赤色まで進む
        new MoveColor().run(maxSpeed, "RED", true);
        // ペットボトルを放す
        new Arm().run(true);
        // マップ中央まで進む
        new Move().run(maxSpeed, -170);
        // 右のペットボトルに向く
        new Turn().run(maxSpeed, 50);
        // ペットボトルまでつっこむ
        new Move().run(maxSpeed, 45);
        // ペットボトルをつかむ
        new Arm().run(false);
        // 置く場所にゆっくり向く
        new Turn().run(100, -70);
        // 置く場所の赤色まで進む
        new MoveColor().run(maxSpeed, "RED", true);
        // ペットボトルを放して下がる
        new Arm().run(false);
        new Move().run(maxSpeed, -15);
        // マップ中央に向く
        new Turn().run(maxSpeed, 15);
        // マップ中央まで進む
        new Move().run(maxSpeed, -160);
        // 左のペットボトルに向く
        new Turn().run(maxSpeed, -50);
        // ペットボトルまでつっこむ
        new Move().run(maxSpeed, 45);
        // ペットボトルをつかむ
        new Arm().run(false);
        // 置く場所のまでゆっくり向く
        new Turn().run(100, 70);
        // 置く場所の赤色まで進む
        new MoveColor().run(maxSpeed, "RED", true);
        // ペットボトルを放して下がる
        new Arm().run(false);
        new Move().run(maxSpeed, -15);
        // マップ中央に向く
        new Turn().run(maxSpeed, -10);
        // 帰宅場所の黄色まで進む
        new MoveColor().run(maxSpeed, "YELLOW", false);
    }
}