package info.keloud.tec.ev3lejos.action;

import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.*;

public class MoveUltrasonic extends AbstractUtil {

    public void run(float speed, float distance, boolean direction) {
        setMaxSpeed(speed);
        setDistance(distance);

        try {
            // スムーズ移動の設定
            leftMotor.setAcceleration(1000);
            rightMotor.setAcceleration(1000);

            //速度設定
            leftMotor.setSpeed(getMaxSpeed());
            rightMotor.setSpeed(getMaxSpeed());

            // 移動開始
            moveLargeMotor(direction);

            // 指定の距離まで移動する
            while (true) {
                if (ultrasonicSensor.getValue() > getDistance()) {
                    stopLargeMotor();
                    break;
                }
            }
        } catch (Exception e) {
            Sound.buzz();
        } finally {
            playStopSound();
        }
    }
}
