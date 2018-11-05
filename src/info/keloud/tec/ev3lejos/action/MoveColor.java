package info.keloud.tec.ev3lejos.action;

import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.*;

public class MoveColor extends AbstractUtil {
    // 前(true)後(false)方向
    private boolean direction;

    public void run(float speed, boolean direction, String color) {
        setMaxSpeed(speed);
        setColorId(color);
        this.direction = direction;
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

            // モーター回転角を設定する
            setAngle(getDistance() / (getTireCircumference() / 360));

            // 移動開始
            if (direction) {
                leftMotor.startSynchronization();
                leftMotor.forward();
                rightMotor.forward();
                leftMotor.endSynchronization();
            } else {
                leftMotor.startSynchronization();
                leftMotor.backward();
                rightMotor.backward();
                leftMotor.endSynchronization();
            }

            // 指定の色を見つけるまで移動する
            while (true) {
                if (colorSensor.getColorID()[0] == getColorId()) {
                    stopLargeMotor();
                    break;
                }
            }
        } catch (Exception e) {
            Sound.buzz();
        } finally {
            Sound.beepSequenceUp();
        }
    }
}