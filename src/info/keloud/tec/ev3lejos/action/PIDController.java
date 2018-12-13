package info.keloud.tec.ev3lejos.action;

import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.*;

public class PIDController extends AbstractUtil {

    public void run(float maxSpeed, float distance) {

        try {
            // 初期化
            int initTachoCount = leftMotor.getTachoCount();
            int tachoCount = 0;
            float currentLeftSpeed;
            float currentRightSpeed;
            leftMotor.setSpeed(getMinSpeed());
            rightMotor.setSpeed(getMinSpeed());

            // 角度累計計算
            float cum = distance2Cumulative(distance);

            //速度から必要な距離を求める(可変距離)
            float distanceVariable = maxSpeed * 0.24F;

            // 移動開始
            leftMotor.startSynchronization();
            leftMotor.forward();
            rightMotor.forward();
            leftMotor.endSynchronization();

            //移動処理
            while (tachoCount < cum) {
                // Current Speed
                float currentSpeed;
                if (tachoCount > cum - distanceVariable) {
                    //減速部
                    currentSpeed = (maxSpeed - getMinSpeed()) * (cum - tachoCount) / distanceVariable + getMinSpeed();
                } else if (tachoCount < distanceVariable) {
                    //加速部
                    currentSpeed = (maxSpeed - getMinSpeed()) * tachoCount / distanceVariable + getMinSpeed();
                } else {
                    //巡航部
                    currentSpeed = maxSpeed;
                }

                float colorValue = colorSensor.getRedValue();
                float targetColorValue = 0.55F;
                currentLeftSpeed = currentSpeed;
                currentRightSpeed = currentSpeed;
                //白色を検知した時
                if (targetColorValue < colorValue) {
                    currentLeftSpeed -= (colorValue - targetColorValue) * 360F;
                }
                //黒色を検知した時
                if (targetColorValue > colorValue) {
                    currentRightSpeed -= (targetColorValue - colorValue) * 360F;
                }

                leftMotor.setSpeed(currentLeftSpeed);
                rightMotor.setSpeed(currentRightSpeed);
                tachoCount = leftMotor.getTachoCount() - initTachoCount;
            }
        } catch (Exception e) {
            Sound.buzz();
        } finally {
            leftMotor.startSynchronization();
            leftMotor.stop();
            rightMotor.stop();
            leftMotor.endSynchronization();
        }
    }

    // 最低の速度を返す
    private float getMinSpeed() {
        return 100;
    }
}
