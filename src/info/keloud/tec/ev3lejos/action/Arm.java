package info.keloud.tec.ev3lejos.action;

import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.centerMotor;
import static info.keloud.tec.ev3lejos.Main.isArmOpen;

public class Arm extends AbstractUtil {
    private boolean state;

    public void run(boolean state) {
        this.state = state;
        run();
    }

    @Override
    public void run() {
        setAngle(290);
        setMaxSpeed(centerMotor.getMaxSpeed());

        if (state) {
            open();
        } else {
            close();
        }
    }

    private void open() {
        try {
            // 初期化
            int initTachoCount = centerMotor.getTachoCount();
            int tachoCount = 0;
            centerMotor.setSpeed(minSpeed);

            // 角度累計計算
            float cum = angle2Cumulative(angle);

            // 移動開始
            centerMotor.forward();

            // 移動処理
            while (tachoCount < cum) {
                tachoCount = centerMotor.getTachoCount() - initTachoCount;
            }
        } catch (Exception e) {
            Sound.buzz();
        } finally {
            Sound.beepSequenceUp();
            // 停止
            centerMotor.stop(true);
            // アームが開いたことにする
            isArmOpen = true;
        }
    }

    private void close() {
        try {
            // 初期化
            int initTachoCount = centerMotor.getTachoCount();
            int tachoCount = 0;
            centerMotor.setSpeed(minSpeed);

            // 角度累計計算
            float cum = distance2Cumulative(distance);

            // 移動開始
            centerMotor.backward();

            //移動処理
            while (tachoCount < cum) {
                tachoCount = -(centerMotor.getTachoCount() - initTachoCount);
            }
        } catch (Exception e) {
            Sound.buzz();
        } finally {
            Sound.beepSequenceUp();
            // 停止
            centerMotor.stop(true);
            // アームが閉じたことにする
            isArmOpen = false;
        }
    }
}