package info.keloud.tec.ev3lejos.action;

import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.leftMotor;
import static info.keloud.tec.ev3lejos.Main.rightMotor;

public class Move extends AbstractUtil {
    public void run(float maxSpeed, float distance) {

        try {
            // スムーズ移動の設定
            leftMotor.setAcceleration(1000);
            rightMotor.setAcceleration(1000);

            //速度設定
            leftMotor.setSpeed(maxSpeed);
            rightMotor.setSpeed(maxSpeed);

            // 移動開始
            leftMotor.startSynchronization();
            leftMotor.rotate((int) getMotorAngle(distance));
            rightMotor.rotate((int) getMotorAngle(distance));
            leftMotor.endSynchronization();

            // 止まるまで待つ
            leftMotor.waitComplete();
            rightMotor.waitComplete();
        } catch (Exception e) {
            Sound.buzz();
        }
    }
}
