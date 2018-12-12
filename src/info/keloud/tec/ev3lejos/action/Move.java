package info.keloud.tec.ev3lejos.action;

import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.leftMotor;
import static info.keloud.tec.ev3lejos.Main.rightMotor;

public class Move extends AbstractUtil {
    public void run(float speed, float distance) {
        setMaxSpeed(speed);
        setDistance(distance);

        try {
            // スムーズ移動の設定
            leftMotor.setAcceleration(1000);
            rightMotor.setAcceleration(1000);

            //速度設定
            leftMotor.setSpeed(getMaxSpeed());
            rightMotor.setSpeed(getMaxSpeed());

            // モーター回転角を設定する
            setMotorAngle(getDistance());

            // 移動開始
            leftMotor.startSynchronization();
            leftMotor.rotate((int) getMotorAngle());
            rightMotor.rotate((int) getMotorAngle());
            leftMotor.endSynchronization();

            leftMotor.startSynchronization();
            leftMotor.waitComplete();
            rightMotor.waitComplete();
            leftMotor.endSynchronization();
        } catch (Exception e) {
            Sound.buzz();
        }
    }
}
