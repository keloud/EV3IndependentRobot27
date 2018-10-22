package info.keloud.tec.ev3lejos.action;

import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.leftMotor;
import static info.keloud.tec.ev3lejos.Main.rightMotor;

public class Forward extends AbstractUtil {
    public void run(float speed, float distance) {
        setMaxSpeed(speed);
        setDistance(distance);
        run();
    }

    @Override
    public void run() {
        try {
            // 初期化
            leftMotor.setSpeed(maxSpeed);
            rightMotor.setSpeed(maxSpeed);

            // モーター回転角を設定する
            setAngle(distance / (getTireCircumference() / 360));

            // 移動開始
            leftMotor.startSynchronization();
            leftMotor.rotateTo((int) angle);
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
