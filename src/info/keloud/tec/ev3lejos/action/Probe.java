package info.keloud.tec.ev3lejos.action;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;

import static info.keloud.tec.ev3lejos.Main.*;

public class Probe extends AbstractUtil {
    public void run(int proveRangeAngle) {
        int maxSpeed = 100;

        // 利用するモーターを選択
        EV3LargeRegulatedMotor motor;
        if (0 < proveRangeAngle) {
            motor = rightMotor;
        } else {
            motor = leftMotor;
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

            // 一番近いスポットへの角度を設定し、移動する
            new Turn().run(100, -getMotorAngle(cumulative2Distance(proveRangeTachoCount - shortRangeTachoCount)));
        } catch (Exception e) {
            Sound.buzz();
        }
    }
}
