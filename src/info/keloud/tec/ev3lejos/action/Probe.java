package info.keloud.tec.ev3lejos.action;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;

import static info.keloud.tec.ev3lejos.Main.*;

public class Probe extends AbstractUtil {
    public void run(int proveRangeAngle) {
        setMaxSpeed(100);
        setAngle(proveRangeAngle);

        // 利用するモーターを選択
        EV3LargeRegulatedMotor motor;
        if (0 < getAngle()) {
            motor = rightMotor;
        } else {
            motor = leftMotor;
        }

        try {
            // スムーズ移動の設定
            leftMotor.setAcceleration(1000);
            rightMotor.setAcceleration(1000);

            //速度設定
            leftMotor.setSpeed(getMaxSpeed());
            rightMotor.setSpeed(getMaxSpeed());

            // 回したい角度からマシンの回転距離を求める
            setDistance(angle2Distance(getAngle()));

            // 回転距離からモーター回転角を設定する
            setMotorAngle(getDistance());

            // 移動開始
            leftMotor.startSynchronization();
            leftMotor.rotate((int) -getMotorAngle(), true);
            rightMotor.rotate((int) getMotorAngle(), true);
            leftMotor.endSynchronization();

            // 探査
            float proveRangeTachoCount = distance2Cumulative(angle2Distance(getAngle()));
            float shortRange = ultrasonicSensor.getValue();
            float currentRange;
            float initTachoCount = motor.getTachoCount();
            float shortRangeTachoCount = 0;
            while (leftMotor.isMoving() || rightMotor.isMoving()) {
                currentRange = ultrasonicSensor.getValue();
                if (currentRange < shortRange) {
                    shortRange = currentRange;
                    shortRangeTachoCount = motor.getTachoCount() - initTachoCount;
                }
            }

            Button.ENTER.waitForPress();

            // 一番近いスポットへの角度を設定する
            setMotorAngle(cumulative2Distance(proveRangeTachoCount - shortRangeTachoCount));

            new Turn().run(100, -getMotorAngle());
        } catch (Exception e) {
            Sound.buzz();
        } finally {
            playStopSound();
        }
    }
}
