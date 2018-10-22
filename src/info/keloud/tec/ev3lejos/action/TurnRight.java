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
            // 初期化
            leftMotor.setSpeed(maxSpeed);
            rightMotor.setSpeed(maxSpeed);

            // 回したい角度からマシンの回転距離を求める
            setDistance(getMachineCircumference() / angle);

            // 回転距離からモーター回転角を設定する
            setAngle(distance / (getTireCircumference() / 360));

            // 移動開始
            leftMotor.startSynchronization();
            leftMotor.rotateTo(-(int) angle);
            rightMotor.rotateTo((int) angle);
            leftMotor.endSynchronization();

            // モーターが止まるまで待つ
            while (leftMotor.isMoving() || rightMotor.isMoving()) {
                sleep();
            }
        } catch (Exception e) {
            Sound.buzz();
        } finally {
            Sound.beepSequenceUp();
        }
    }
}