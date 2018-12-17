package info.keloud.tec.ev3lejos.action;

import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.*;

public class Probe extends AbstractUtil {
    public void run(int proveRangeAngle) {
        int maxSpeed = 100;

        // 利用するモーターを選択
        if (0 < proveRangeAngle) {

        } else {

        }

        try {
            // スムーズ移動の設定
            leftMotor.setAcceleration(1000);
            rightMotor.setAcceleration(1000);

            //速度設定
            leftMotor.setSpeed(maxSpeed);
            rightMotor.setSpeed(maxSpeed);

            // 移動開始
            leftMotor.startSynchronization();
            leftMotor.rotate((int) -getMotorAngle(angle2Distance(proveRangeAngle)), true);
            rightMotor.rotate((int) getMotorAngle(angle2Distance(proveRangeAngle)), true);
            leftMotor.endSynchronization();

            // 探査
            float proveRangeTachoCount = distance2Cumulative(angle2Distance(proveRangeAngle));
            float shortRange = ultrasonicSensor.getValue();
            float currentRange;

            int initLeftTachoCount = leftMotor.getTachoCount();
            int initRightTachoCount = rightMotor.getTachoCount();
            int shortRangeLeftTachoCount = 0;
            int shortRangeRightTachoCount = 0;
            while (leftMotor.isMoving() || rightMotor.isMoving()) {
                currentRange = ultrasonicSensor.getValue();
                if (currentRange < shortRange) {
                    shortRange = currentRange;
                    shortRangeLeftTachoCount = leftMotor.getTachoCount() - initLeftTachoCount;
                    shortRangeRightTachoCount = rightMotor.getTachoCount() - initRightTachoCount;
                }
            }

            // 止まるまで待つ
            leftMotor.waitComplete();
            rightMotor.waitComplete();

            // 一番近いスポットへの角度を設定し、移動する
            leftMotor.startSynchronization();
            leftMotor.rotateTo(shortRangeLeftTachoCount);
            rightMotor.rotateTo(shortRangeRightTachoCount);
            leftMotor.endSynchronization();

            // 止まるまで待つ
            leftMotor.waitComplete();
            rightMotor.waitComplete();
        } catch (Exception e) {
            Sound.buzz();
        }
    }
}
