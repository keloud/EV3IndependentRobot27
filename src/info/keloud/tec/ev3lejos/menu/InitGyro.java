package info.keloud.tec.ev3lejos.menu;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

import static info.keloud.tec.ev3lejos.Main.gyroSensor;

public class InitGyro {
    public InitGyro() {
        //ディスプレイ案内の更新
        LCD.clear(4);
        LCD.drawString("Gyro   :" + gyroSensor.getValue(), 1, 4);
        LCD.clear(5);
        LCD.drawString("Re Init Gyro ?", 1, 5);
        LCD.clear(6);
        LCD.drawString("Init     -> Enter", 1, 6);
        LCD.clear(7);
        LCD.drawString("As it is -> Any", 1, 7);
        LCD.refresh();

        boolean flag = true;
        while (flag) {
            if (Button.waitForAnyPress(1) == Button.ID_ENTER) {//現在のアームを(開いている)にする
                gyroSensor.initGyro();
                flag = true;
            } else {
                flag = false;
            }
            LCD.clear(4);
            LCD.drawString("Gyro   :" + gyroSensor.getValue(), 1, 4);
        }
    }
}
