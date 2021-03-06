package info.keloud.tec.ev3lejos.action;

import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.*;

// 指定した色まで前進する
public class MoveColor extends AbstractUtil {
    public void run(float maxSpeed, String color, boolean direction) {
        int colorId = getColorId(color);

        try {
            // スムーズ移動の設定
            leftMotor.setAcceleration(1000);
            rightMotor.setAcceleration(1000);

            //速度設定
            leftMotor.setSpeed(maxSpeed);
            rightMotor.setSpeed(maxSpeed);

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
                if (colorSensor.getColorID() == colorId) {
                    leftMotor.startSynchronization();
                    leftMotor.stop();
                    rightMotor.stop();
                    leftMotor.endSynchronization();
                    break;
                }
            }
        } catch (Exception e) {
            Sound.buzz();
        }
    }

    // 前進用短縮メソッド
    public void run(float maxSpeed, String color) {
        this.run(maxSpeed, color, true);
    }
}