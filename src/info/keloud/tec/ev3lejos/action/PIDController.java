package info.keloud.tec.ev3lejos.action;

import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.*;

public class PIDController extends AbstractUtil {
    public void run(float speed, float distance) {
        setMaxSpeed(speed);
        setDistance(distance);
        run();
    }

    @Override
    public void run() {
        try {
            // 初期化
            int initTachoCount = leftMotor.getTachoCount();
            int tachoCount = 0;
            float currentLeftSpeed = getCurrentSpeed();
            float currentRightSpeed = getCurrentSpeed();
            leftMotor.setSpeed(getMinSpeed());
            rightMotor.setSpeed(getMinSpeed());

            // 角度累計計算
            float cum = distance2Cumulative(getDistance());

            //速度から必要な距離を求める(可変距離)
            float distanceVariable = getMaxSpeed() * 0.24F;

            // 移動開始
            leftMotor.startSynchronization();
            leftMotor.forward();
            rightMotor.forward();
            leftMotor.endSynchronization();

            //移動処理
            while (tachoCount < cum) {
                if (tachoCount > cum - distanceVariable) {
                    //減速部
                    setCurrentSpeed((getMaxSpeed() - getMinSpeed()) * (cum - tachoCount) / distanceVariable + getMinSpeed());
                } else if (tachoCount < distanceVariable) {
                    //加速部
                    setCurrentSpeed((getMaxSpeed() - getMinSpeed()) * tachoCount / distanceVariable + getMinSpeed());
                } else {
                    //巡航部
                    setCurrentSpeed(getMaxSpeed());
                }

                float colorValue = colorSensor.getRedValue()[0];
                float targetColorValue = 0.55F;
                currentLeftSpeed = getCurrentSpeed();
                currentRightSpeed = getCurrentSpeed();
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
            stopLargeMotor();
        }
    }
}
