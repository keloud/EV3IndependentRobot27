package info.keloud.tec.ev3lejos;

import lejos.hardware.lcd.LCD;

public class Main {
    protected static ModeChangeListener modeChangeListener;

    public static void main(String[] args) {
        Robot robot = new Robot();
    }

    private static class Robot implements ModeChangeListenerInterface {
        Robot() {
            //動作変更通知を定義する
            modeChangeListener = new ModeChangeListener();
            modeChangeListener.setListener(this);
            modeChangeListener.setModeName("Initializing");
        }

        @Override
        public void onModeChanged() {
            LCD.clear(8);
            LCD.drawString(modeChangeListener.getModeName(), 0, 8);
            LCD.refresh();
        }
    }
}
