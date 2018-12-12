package info.keloud.tec.ev3lejos.action;

import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.leftMotor;
import static info.keloud.tec.ev3lejos.Main.rightMotor;

public class Turn extends AbstractUtil {
    public void run(float speed, float angle) {
        setMaxSpeed(speed);
        setAngle(angle);

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
            setMotorAngle(getDistance());

            // 移動開始
            leftMotor.startSynchronization();
            leftMotor.rotate((int) -getMotorAngle());
            rightMotor.rotate((int) getMotorAngle());
            leftMotor.endSynchronization();

            leftMotor.startSynchronization();
            leftMotor.waitComplete();
            rightMotor.waitComplete();
            leftMotor.endSynchronization();
        } catch (Exception e) {
            Sound.buzz();
        }
    }
}