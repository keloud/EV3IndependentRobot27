package info.keloud.tec.ev3lejos;

import info.keloud.tec.ev3lejos.action.*;
import lejos.hardware.Button;
import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.*;

class EV3 {
    void run() {
        // Enterキーを押して次に進む 54 + 42 + 9 + 44
        Button.ENTER.waitForPress();
        sensorUpdater.setStopwatchFlag(true);

        // センサ情報の初期化
        gyroSensor.initGyro();
        leftMotor.resetTachoCount();
        rightMotor.resetTachoCount();

        // 音を鳴らす
        Sound.beepSequenceUp();

        // 予選プログラム
        //preliminaryButtle();

        // 決勝プログラム
        finalButtle();

        // 探索テスト
        //new Probe().run(-90);

        // ライントレーサー
        //new PIDController().run(800, 100);

        // 超音波センサを用いた近接走行テスト
        //new MoveUltrasonic().run(800, 0.1F);

        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.stopUpdaterFlag();
    }

    private void bottles() {
        // 最大速度
        float maxSpeed = 800;

        // アームを空ける
        new Arm().run();
        // 真ん中のペットボトルにつっこむ
        new Move().run(maxSpeed, 48);
        // ペットボトルをつかむ
        new Arm().run();
        // 置く場所の赤色まで進む
        new MoveColor().run(maxSpeed, "RED");
        // ペットボトルを放す
        new Arm().run();
        // マップ中央まで進む
        new Move().run(maxSpeed, -160);
        // 右のペットボトルに向く
        new Turn().run(maxSpeed, -50);
        // ペットボトルまでつっこむ
        new Move().run(maxSpeed, 50);
        // ペットボトルをつかむ
        new Arm().run();
        // 置く場所にゆっくり向く
        new Turn().run(100, 65);
        // 置く場所の赤色まで進む
        new MoveColor().run(maxSpeed, "RED");
        // ペットボトルを放して下がる
        new Arm().run();
        new Move().run(maxSpeed, -15);
        // マップ中央に向く
        new Turn().run(maxSpeed, -15);
        // マップ中央まで進む
        new Move().run(maxSpeed, -145);
        // 左のペットボトルに向く
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
    }

    private void finalButtle() {
        // 最大速度
        float maxSpeed = 800;

        // 決勝時間(2分に設定)
        sensorUpdater.setFinalButtle();

        // 3本のワークを取得する
        bottles();

        // センサ情報の初期化
        Sound.beepSequenceUp();
        gyroSensor.initGyro();
        leftMotor.resetTachoCount();
        rightMotor.resetTachoCount();

        // 追加ワーク範囲中央へ進む
        new Move().run(maxSpeed, -105);
        // 探索
        gyroSensor.initGyro();
        new Probe().run(350);
        // 探索した向きへ進む
        new MoveUltrasonic().run(maxSpeed, 0.4F);
        // 探索
        new Turn().run(500, -30);
        new Probe().run(70);
        // 探索した向きに進む
        new MoveUltrasonic().run(maxSpeed, 0.1F);
        // ペットボトルをつかむ
        new Arm().run();
        // 緑の線に向く
        new Turn().run(100, 180);
        // 緑の線まで行く
        new MoveColor().run(100, "GREEN");
        // 前に向く
        float gyro = gyroSensor.getValue();
        new Turn().run(100, (gyro / 360) + 90);
        // 置く場所の赤色まで進む
        new MoveColor().run(maxSpeed, "RED");
        // ペットボトルを放して下がる
        new Arm().run();
        new Move().run(maxSpeed, -15);
    }

    private void preliminaryButtle() {
        // 最大速度
        float maxSpeed = 800;

        // 予選時間(1分に設定)
        sensorUpdater.setPreliminaryButtle();

        // 3本のワークを取得する
        bottles();

        // 帰宅場所の黄色まで進む
        new MoveColor().run(maxSpeed, "YELLOW", false);
    }
}