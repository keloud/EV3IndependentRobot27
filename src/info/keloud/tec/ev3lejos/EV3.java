package info.keloud.tec.ev3lejos;

import lejos.hardware.Button;

import static info.keloud.tec.ev3lejos.Main.*;

class EV3 {
    EV3() {

    }

    void run() {
        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.setStopwatchFlag(true);

        //モーター設定
        float speedInit = 120;
        leftMotor.setSpeed(speedInit);
        rightMotor.setSpeed(speedInit);

        //モーターを動作させる
        leftMotor.startSynchronization();
        leftMotor.forward();
        rightMotor.forward();
        leftMotor.endSynchronization();

        //モーターを指定まで動作させ続ける
        //現在のタコカウントを取得する
        int tachoCount = leftMotor.getTachoCount();
        //走らせたい郷里に必要なタコカウントを求める
        float cumulative = getCumulative(100) + tachoCount;
        //最大速度
        int maxSpeed = 800;
        while (tachoCount <= cumulative) {
            //加減速処理
            float speed;
            if (cumulative - getCumulative(maxSpeed / 26) < tachoCount) {
                //減速
                speed = (maxSpeed - speedInit) * (cumulative - tachoCount) / getCumulative(maxSpeed / 26) + speedInit;
            } else if (tachoCount < getCumulative(5)) {
                //加速
                speed = (maxSpeed - speedInit) * tachoCount / getCumulative(5) + speedInit;
            } else {
                speed = maxSpeed;
            }

            //更新処理
            leftMotor.setSpeed(speed);
            rightMotor.setSpeed(speed);
            tachoCount = leftMotor.getTachoCount();
        }

        //モーターを止める
        leftMotor.startSynchronization();
        leftMotor.stop(true);
        rightMotor.stop(true);
        leftMotor.endSynchronization();

        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.stopUpdaterFlag();
    }

    //centimeter単位で指定
    //タコカウント用に変換する
    private float getCumulative(int distance) {
        //タイヤ直径
        float diameter = 5.6F;
        //指定した距離に必要なタコカウント
        return (distance / diameter / (float) Math.PI) * 360;
    }
}
