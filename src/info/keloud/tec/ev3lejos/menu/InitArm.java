package info.keloud.tec.ev3lejos.menu;

import info.keloud.tec.ev3lejos.action.Arm;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

import static info.keloud.tec.ev3lejos.Main.isArmOpen;

public class InitArm {
    public InitArm() {
        //ディスプレイ案内の更新
        LCD.clear(5);
        LCD.drawString("Close the arm ?", 1, 5);
        LCD.clear(6);
        LCD.drawString("Close    -> Enter", 1, 6);
        LCD.clear(7);
        LCD.drawString("As it is -> Any", 1, 7);
        LCD.refresh();

        switch (Button.waitForAnyPress()) {
            case Button.ID_ENTER:
                isArmOpen = true;
                new Arm().run(true);
                break;
            default:
                break;
        }
    }
}
