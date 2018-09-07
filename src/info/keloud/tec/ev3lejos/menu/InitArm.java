package info.keloud.tec.ev3lejos.menu;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;

import static info.keloud.tec.ev3lejos.Main.centerMotor;

public class InitArm {
    public InitArm() {
        //ディスプレイ案内の更新
        LCD.clear(5);
        LCD.drawString("Close the arm?", 1, 5);
        LCD.clear(6);
        LCD.drawString("Y -> Enter", 1, 6);
        LCD.clear(7);
        LCD.drawString("N -> Any", 1, 7);
        LCD.refresh();

        switch (Button.waitForAnyPress()) {
            case Button.ID_ENTER:
                armClose();
                break;
            default:
                break;
        }
    }

    private void armClose() {
        try {
            //モーターを動作させ続ける
            //タコカウントの初期値を設定する
            int initTachoCount = centerMotor.getTachoCount();
            //タコカウントを初期化する
            int tachoCount = 0;
            //走らせたい郷里に必要なタコカウントを求める
            float cumulative = 380;
            //巡航速度
            centerMotor.setSpeed(300);

            //モーターを動作させる
            centerMotor.backward();

            //更新処理
            while (tachoCount <= cumulative) {
                tachoCount = -(centerMotor.getTachoCount() - initTachoCount);
            }
        } catch (Exception e) {
            Sound.buzz();
        } finally {
            Sound.beepSequenceUp();
            centerMotor.stop(true);
        }
    }
}
