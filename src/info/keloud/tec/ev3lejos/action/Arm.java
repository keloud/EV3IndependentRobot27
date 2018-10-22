package info.keloud.tec.ev3lejos.action;

import info.keloud.tec.ev3lejos.Main;
import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.centerMotor;

public class Arm extends AbstractUtil {
    @Override
    public void run() {
        setMaxSpeed(centerMotor.getMaxSpeed());
        centerMotor.setAcceleration(1200);

        // アーム開閉状態を取得し、開閉を決める
        // trueならば開いている
        if (Main.isArmOpen) {
            setAngle(-380);
            close();
        } else {
            setAngle(340);
            open();
        }
    }

    private void open() {
        try {
            // 初期化
            centerMotor.setSpeed(maxSpeed);

            // 移動処理
            centerMotor.rotateTo((int) angle);

            while (centerMotor.isMoving()) {
                wait(1);
            }
        } catch (Exception e) {
            Sound.buzz();
        } finally {
            Sound.beepSequenceUp();
            // アームが開いたことにする
            Main.isArmOpen = true;
        }
    }

    private void close() {
        try {
            // 初期化
            centerMotor.setSpeed(maxSpeed);

            // 移動処理
            centerMotor.rotateTo((int) angle);

            while (centerMotor.isMoving()) {
                wait(1);
            }
        } catch (Exception e) {
            Sound.buzz();
        } finally {
            Sound.beepSequenceUp();
            // アームが閉じたことにする
            Main.isArmOpen = false;
        }
    }
}