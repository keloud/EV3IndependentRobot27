package info.keloud.tec.ev3lejos.action;

import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.leftMotor;
import static info.keloud.tec.ev3lejos.Main.rightMotor;

public class Forward extends AbstractUtil {
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
            leftMotor.setSpeed(minSpeed);
            rightMotor.setSpeed(minSpeed);

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
                if (tachoCount > cum - distanceVariable) {
                    //減速部
                    currentSpeed = ((maxSpeed - minSpeed) * (cum - tachoCount) / distanceVariable + minSpeed);
                } else if (tachoCount < distanceVariable) {
                    //加速部
                    currentSpeed = ((maxSpeed - minSpeed) * tachoCount / distanceVariable + minSpeed);
                } else {
                    //巡航部
                    currentSpeed = maxSpeed;
                }
                leftMotor.setSpeed(currentSpeed);
                rightMotor.setSpeed(currentSpeed);
                tachoCount = leftMotor.getTachoCount() - initTachoCount;
            }
        } catch (Exception e) {
            Sound.buzz();
        } finally {
            Sound.beepSequenceUp();
            // 停止
            leftMotor.startSynchronization();
            leftMotor.stop(true);
            rightMotor.stop(true);
            leftMotor.endSynchronization();
        }
    }
}
