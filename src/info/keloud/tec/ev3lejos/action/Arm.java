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

            // モーター回転角を設定し、移動開始
            centerMotor.rotate((int) angle);

            // モーターが止まるまで待つ
            while (centerMotor.isMoving()) {
                sleep();
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

            // モーター回転角を設定する
            centerMotor.rotate((int) angle);

            // モーターが止まるまで待つ
            while (centerMotor.isMoving()) {
                sleep();
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