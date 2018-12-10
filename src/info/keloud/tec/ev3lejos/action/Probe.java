package info.keloud.tec.ev3lejos.action;

import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.*;

public class Probe extends AbstractUtil {
    public void run(int proveRangeAngle) {
        setMaxSpeed(100);
        setAngle(proveRangeAngle);

        try {
            // スムーズ移動の設定
            leftMotor.setAcceleration(1000);
            rightMotor.setAcceleration(1000);

            //速度設定
            leftMotor.setSpeed(getMaxSpeed());
            rightMotor.setSpeed(getMaxSpeed());

            // 回したい角度からマシンの回転距離を求める
            setDistance(angle2Distance(getAngle()));

            // 回転距離からモーター回転角を設定する
            setAngle(getDistance());

            // 移動開始
            leftMotor.startSynchronization();
            leftMotor.rotate((int) -getAngle(), true);
            rightMotor.rotate((int) getAngle(), true);
            leftMotor.endSynchronization();

            // 探査
            float shortRange = ultrasonicSensor.getValue();
            float currentRange;
            float initAngle = gyroSensor.getValue();
            float shortRangeAngle = 0;
            while (leftMotor.isMoving() || rightMotor.isMoving()) {
                currentRange = ultrasonicSensor.getValue();
                if (currentRange < shortRange) {
                    shortRange = currentRange;
                    shortRangeAngle = gyroSensor.getValue() - initAngle;
                }
            }

            // 一番近いスポットへの角度を設定する
            setAngle(-(proveRangeAngle - shortRangeAngle));

            new Turn().run(100, getAngle());
        } catch (Exception e) {
            Sound.buzz();
        } finally {
            playStopSound();
        }
    }
}
