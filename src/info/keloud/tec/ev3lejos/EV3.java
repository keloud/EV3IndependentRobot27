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
        //走らせたい距離に必要なタコカウントを求める
        float cumulative = getCumulative(10000) + tachoCount;
        //最大速度
        int maxSpeed = 200;

        float initRightTacho = rightMotor.getTachoCount();
        float initLeftTacho = leftMotor.getTachoCount();
        while (tachoCount <= cumulative) {
            //加減速処理
            float speed;
            if (cumulative - getCumulative(maxSpeed / 24) < tachoCount) {
                //減速
                speed = (maxSpeed - speedInit) * (cumulative - tachoCount) / getCumulative(maxSpeed / 24) + speedInit;
            } else if (tachoCount < getCumulative(5)) {
                //加速
                speed = (maxSpeed - speedInit) * tachoCount / getCumulative(5) + speedInit;
            } else {
                speed = maxSpeed;
            }

            //カラーセンサー
            float speedRight = speed;
            float speedLeft = speed;
            float colorValue = colorSensor.getValue();

            //いい感じ制御(加速を流用する)
            if (0.8 < colorValue) {
                initLeftTacho = leftMotor.getTachoCount();
                float rightTacho = rightMotor.getTachoCount() - initRightTacho;
                speedRight = (maxSpeed * 1.6F - speed) * rightTacho / getCumulative(4) + speed;
            } else if (colorValue < 0.4) {
                initRightTacho = rightMotor.getTachoCount();
                float leftTacho = leftMotor.getTachoCount() - initLeftTacho;
                speedLeft = (maxSpeed * 1.6F - speed) * leftTacho / getCumulative(4) + speed;
            } else {
                initLeftTacho = leftMotor.getTachoCount();
                initRightTacho = rightMotor.getTachoCount();
            }

            /*
            On Off制御
            if (0.68 < colorValue) {
                speedRight = speed * 1.38F;
            } else if (colorValue < 0.5){
                speedLeft = speed * 1.68F;
            }
            */

            //更新処理
            leftMotor.setSpeed(speedLeft);
            rightMotor.setSpeed(speedRight);
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
