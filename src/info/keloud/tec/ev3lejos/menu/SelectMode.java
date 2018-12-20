package info.keloud.tec.ev3lejos.menu;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

import static info.keloud.tec.ev3lejos.Main.mode;

public class SelectMode {
    public SelectMode() {
        //ディスプレイ案内の更新
        LCD.clear(5);
        LCD.drawString("Select Mode", 1, 5);
        LCD.clear(6);
        LCD.drawString("Preliminary-> Enter", 1, 6);
        LCD.clear(7);
        LCD.drawString("Final      -> Any", 1, 7);
        LCD.refresh();

        mode = Button.waitForAnyPress() == Button.ID_ENTER;
    }
}
