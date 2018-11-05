package info.keloud.tec.ev3lejos.action;

import info.keloud.tec.ev3lejos.Main;
import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.centerMotor;

public class Arm extends AbstractUtil {
    @Override
    public void run() {
        //動作中ならば待つ
        while (centerMotor.isMoving()) {
            // 待機
        }

        // スムーズ移動の設定
        centerMotor.setAcceleration(1200);

        // 速度設定
        setMaxSpeed(centerMotor.getMaxSpeed());
        centerMotor.setSpeed(getMaxSpeed());

        // アーム開閉状態を取得し、開閉を決める
        // trueならば開いている
        if (Main.isArmOpen) {
            setAngle(-360);
            close();
        } else {
            setAngle(360);
            open();
        }
    }

    private void open() {
        try {
            // モーター回転角を設定し、移動開始
            centerMotor.rotate((int) getAngle(), true);
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
            // モーター回転角を設定する
            centerMotor.rotate((int) getAngle(), true);
        } catch (Exception e) {
            Sound.buzz();
        } finally {
            Sound.beepSequenceUp();
            // アームが閉じたことにする
            Main.isArmOpen = false;
        }
    }
}