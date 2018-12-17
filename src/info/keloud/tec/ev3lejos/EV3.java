package info.keloud.tec.ev3lejos;

import info.keloud.tec.ev3lejos.action.Arm;
import info.keloud.tec.ev3lejos.action.Move;
import info.keloud.tec.ev3lejos.action.MoveColor;
import info.keloud.tec.ev3lejos.action.Turn;
import lejos.hardware.Button;

import static info.keloud.tec.ev3lejos.Main.gyroSensor;
import static info.keloud.tec.ev3lejos.Main.sensorUpdater;

class EV3 {
    void run() {
        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.setStopwatchFlag(true);

        // センサ情報の初期化
        gyroSensor.initGyro();

        // 予選プログラム
        yosen();

        // 本選プログラム

        // 探索テスト
        //new Probe().run(90);

        // ライントレーサー
        //new PIDController().run(800, 100);

        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.stopUpdaterFlag();
    }

    private void yosen() {
        float maxSpeed = 800;

        // アームを空ける
        new Arm().run();
        // 真ん中のペットボトルにつっこむ
        new Move().run(maxSpeed, 48);
        // ペットボトルをつかむ
        new Arm().run();
        // 置く場所の赤色まで進む
        new MoveColor().run(maxSpeed, "RED", true);
        // ペットボトルを放す
        new Arm().run();
        // マップ中央まで進む
        new Move().run(maxSpeed, -160);
        // 右のペットボトルに向く
        //new Probe().run(-75);
        new Turn().run(maxSpeed, -50);
        // ペットボトルまでつっこむ
        new Move().run(maxSpeed, 50);
        // ペットボトルをつかむ
        new Arm().run();
        // 置く場所にゆっくり向く
        new Turn().run(100, 75);
        // 置く場所の赤色まで進む
        new MoveColor().run(maxSpeed, "RED", true);
        // ペットボトルを放して下がる
        new Arm().run();
        new Move().run(maxSpeed, -15);
        // マップ中央に向く
        new Turn().run(maxSpeed, -gyroSensor.getValue());
        // マップ中央まで進む
        new Move().run(maxSpeed, -145);
        // 左のペットボトルに向く
        //new Probe().run(90);
        new Turn().run(maxSpeed, 50);
        // ペットボトルまでつっこむ
        new Move().run(maxSpeed, 50);
        // ペットボトルをつかむ
        new Arm().run();
        // 置く場所のまでゆっくり向く
        new Turn().run(100, -65);
        // 置く場所の赤色まで進む
        new MoveColor().run(maxSpeed, "RED", true);
        // ペットボトルを放して下がる
        new Arm().run();
        new Move().run(maxSpeed, -15);
        // マップ中央に向く
        new Turn().run(maxSpeed, 15);
        // 帰宅場所の黄色まで進む
        new MoveColor().run(maxSpeed, "YELLOW", false);
    }
}