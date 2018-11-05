package info.keloud.tec.ev3lejos.action;

import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.leftMotor;
import static info.keloud.tec.ev3lejos.Main.rightMotor;

public class TurnRight extends AbstractUtil {
    public void run(float speed, float angle) {
        setMaxSpeed(speed);
        setAngle(angle);
        run();
    }

    @Override
    public void run() {
        try {
            // スムーズ移動の設定
            leftMotor.setAcceleration(1000);
            rightMotor.setAcceleration(1000);

            //速度設定
            leftMotor.setSpeed(getMaxSpeed());
            rightMotor.setSpeed(getMaxSpeed());

            // 回したい角度からマシンの回転距離を求める
            setDistance(getMachineCircumference() / (360 / getAngle()));

            // 回転距離からモーター回転角を設定する
            setAngle(getDistance() / (getTireCircumference() / 360));

            // 移動開始
            leftMotor.startSynchronization();
            leftMotor.rotate((int) getAngle());
            rightMotor.rotate((int) -getAngle());
            leftMotor.endSynchronization();
        } catch (Exception e) {
            Sound.buzz();
        } finally {
            Sound.beepSequenceUp();
        }
    }
}