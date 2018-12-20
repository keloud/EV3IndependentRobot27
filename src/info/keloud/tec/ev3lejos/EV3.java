package info.keloud.tec.ev3lejos;

import info.keloud.tec.ev3lejos.action.*;
import lejos.hardware.Button;
import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.*;

class EV3 {
    void run() {
        // Enterキーを押して次に進む
        while (true) {
            int button = Button.waitForAnyPress();
            if (button == Button.ID_ENTER) {
                break;
            }
            if (button == Button.ID_DOWN) {
                gyroSensor.initGyro();
            }
        }
        sensorUpdater.setStopwatchFlag(true);

        /* 3秒待つ
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            Sound.buzz();
        }
        */

        // 音を鳴らす
        Sound.beepSequenceUp();

        // 探索テスト
        //new Probe().run(70);

        // ライントレーサー
        //new PIDController().run(800, 100);

        // 超音波センサを用いた近接走行テスト
        //new MoveUltrasonic().run(800, 0.1F);

        if (mode) {
            // 予選プログラム
            preliminaryButtle();
        } else {
            // 決勝プログラム
            finalBattle();
        }

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
        new Move().run(maxSpeed, -155);
        // 右のペットボトルに向く
        new Turn().run(maxSpeed, -50);
        // ペットボトルまでつっこむ
        new Move().run(maxSpeed, (ultrasonicSensor.getValue() * 100) + 1);
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
        // 左のペットボトルに探査する
        if (0 == new Probe().run(70)) {
            new Turn().run(300, -20);
        }
        // ペットボトルまでつっこむ
        new Move().run(maxSpeed, (ultrasonicSensor.getValue() * 100) + 1);
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

    private void finalBattle() {// 決勝時間(2分に設定)
        sensorUpdater.setFinalButtle();

        // 3本のワークを取得する
        bottles();

        // 1本目
        bottles2();

        // 2本目
        bottles2();
    }

    private void bottles2() {
        // 最大速度
        float maxSpeed = 800;

        // 追加ワーク範囲中央へ進む
        new Move().run(maxSpeed, -100);
        // 探索
        int prove = new Probe().run(180);
        if (prove == 0) {
            new Probe().run(170);
        }
        // 探索した向きへ進む
        float distanceBottle = ultrasonicSensor.getValue() * 100;
        if (30 <= distanceBottle) {
            new Move().run(maxSpeed, (ultrasonicSensor.getValue() * 100) - 15);
        }
        // 探索
        new Turn().run(500, -30);
        new Probe().run(70);
        // 探索した向きに進む
        new Move().run(300, (ultrasonicSensor.getValue() * 100) + 5);
        // ペットボトルをつかむ
        new Arm().run();
        // 緑の線に向く
        new Turn().run(100, 180);
        // 緑の線まで行く
        new MoveColor().run(300, "GREEN");
        // 前に向く
        new Turn().run(100, -(gyroSensor.getValue() % 360));
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