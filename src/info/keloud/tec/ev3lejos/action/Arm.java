package info.keloud.tec.ev3lejos.action;

import info.keloud.tec.ev3lejos.Main;
import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.centerMotor;

public class Arm extends AbstractUtil {
    private int angle = 360;

    public void run() {
        // スムーズ移動の設定
        centerMotor.setAcceleration(8000);

        // 速度設定
        centerMotor.setSpeed(centerMotor.getMaxSpeed());

        // アーム開閉状態を取得し、開閉を決める
        // trueならば開いている
        if (Main.isArmOpen) {
            angle = -angle;
        }

        try {
            // 移動開始
            centerMotor.rotate(angle, true);
        } catch (Exception e) {
            Sound.buzz();
        } finally {
            Main.isArmOpen = !Main.isArmOpen;
        }
    }
}