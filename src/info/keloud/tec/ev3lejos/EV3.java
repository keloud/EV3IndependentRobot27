package info.keloud.tec.ev3lejos;

import lejos.hardware.Button;
import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.*;

class EV3 {
    EV3() {

    }

    void run() {
        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.setStopwatchFlag(true);

        try {
            //モーターを動作させ続ける
            //タコカウントの初期値を設定する
            int initTachoCount = leftMotor.getTachoCount();
            //現在のタコカウントを取得する
            int tachoCount = 0;
            //走らせたい郷里に必要なタコカウントを求める
            float cumulative = getCumulative(80);

            //巡航速度
            float maxSpeed = 800;

            //モーター設定
            float speedInit = 120;
            leftMotor.setSpeed(speedInit);
            rightMotor.setSpeed(speedInit);

            //モーターを動作させる
            leftMotor.startSynchronization();
            leftMotor.forward();
            rightMotor.forward();
            leftMotor.endSynchronization();

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

                //スピード
                float speedRight = speed;
                float speedLeft = speed;

                //タッチセンサ
                if (touchSensor.getValue() == 1.0) {
                    Sound.beep();
                }

                /*超音波
                float usonicValue = ultrasonicSensor.getValue();
                float targetUSonicValue = 0.5F;
                if (targetUSonicValue < usonicValue) {
                    leftMotor.forward();
                    rightMotor.forward();
                } else {
                    leftMotor.backward();
                    rightMotor.backward();
                }*/

                /*カラーセンサー用

                //カラーセンサー
                float colorValue = colorSensor.getValue();

                //坂本P制御
                float targetColorValue = 0.55F;
                //白色を検知した時
                if (targetColorValue < colorValue) {
                    speedLeft -= (colorValue - targetColorValue) * 360F;
                }
                //黒色を検知した時
                if (targetColorValue > colorValue) {
                    speedRight -= (targetColorValue - colorValue) * 360F;
                }

                /*
                //いい感じ制御(加速を流用する)
                if (0.95 < colorValue) {
                    //白左に行きたい
                    initLeftTacho = leftMotor.getTachoCount();
                    float rightTacho = rightMotor.getTachoCount() - initRightTacho;
                    speedRight += (maxSpeed * 0.35F) * rightTacho / getCumulative(4);
                } else if (colorValue < 0.4) {
                    //黒右に行きたい
                    initRightTacho = rightMotor.getTachoCount();
                    float leftTacho = leftMotor.getTachoCount() - initLeftTacho;
                    speedLeft += (maxSpeed * 0.35F) * leftTacho / getCumulative(4);
                } else {
                    //間
                    initLeftTacho = leftMotor.getTachoCount();
                    initRightTacho = rightMotor.getTachoCount();
                }
                /*

                /*
                //On Off制御
                if (0.68 < colorValue) {
                    speedRight = speed * 1.38F;
                } else if (colorValue < 0.5) {
                    speedLeft = speed * 1.68F;
                }
                */


                //更新処理
                leftMotor.setSpeed(speedLeft);
                rightMotor.setSpeed(speedRight);
                tachoCount = leftMotor.getTachoCount() - initTachoCount;
            }
        } catch (Exception e) {
            Sound.buzz();
        } finally {
            Sound.beepSequenceUp();
            //モーターを止める
            leftMotor.startSynchronization();
            leftMotor.stop(true);
            rightMotor.stop(true);
            leftMotor.endSynchronization();
        }

        //後ろに下がる
        try {
            //モーターを動作させ続ける
            //タコカウントの初期値を設定する
            int initTachoCount = leftMotor.getTachoCount();
            //現在のタコカウントを取得する
            int tachoCount = 0;
            //走らせたい距離に必要なタコカウントを求める
            float cumulative = getCumulative(80);

            //巡航速度
            float maxSpeed = 800;

            //モーター設定
            float speedInit = 120;
            leftMotor.setSpeed(speedInit);
            rightMotor.setSpeed(speedInit);

            //モーターを動作させる
            leftMotor.startSynchronization();
            leftMotor.backward();
            rightMotor.backward();
            leftMotor.endSynchronization();

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

                //スピード
                float speedRight = speed;
                float speedLeft = speed;

                //タッチセンサ
                if (touchSensor.getValue() == 1.0) {
                    Sound.beep();
                }

                //更新処理
                leftMotor.setSpeed(speedLeft);
                rightMotor.setSpeed(speedRight);
                tachoCount = -(leftMotor.getTachoCount() - initTachoCount);
            }
        } catch (Exception e) {
            Sound.buzz();
        } finally {
            Sound.beepSequenceUp();
            //モーターを止める
            leftMotor.startSynchronization();
            leftMotor.stop(true);
            rightMotor.stop(true);
            leftMotor.endSynchronization();
        }

        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.stopUpdaterFlag();
    }

    //centimeter単位で指定
    //タコカウント用に変換する
    private float getCumulative(float distance) {
        //タイヤ直径
        float diameter = 5.6F;
        //指定した距離に必要なタコカウント
        return (distance / diameter / (float) Math.PI) * 360;
    }
}