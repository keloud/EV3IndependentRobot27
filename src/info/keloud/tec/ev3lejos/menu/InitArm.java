package info.keloud.tec.ev3lejos.menu;

import info.keloud.tec.ev3lejos.action.Arm;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

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
                new Arm().run(false);
                break;
            default:
                break;
        }
    }
}
