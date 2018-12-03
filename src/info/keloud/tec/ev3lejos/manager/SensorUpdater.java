package info.keloud.tec.ev3lejos.manager;

import lejos.hardware.Battery;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.utility.Stopwatch;

import static info.keloud.tec.ev3lejos.Main.*;

public class SensorUpdater extends Thread {
    private Stopwatch stopwatch;
    private boolean updaterFlag;
    private boolean stopwatchFlag;

    public SensorUpdater() {
        stopwatch = new Stopwatch();
        updaterFlag = true;
        stopwatchFlag = false;
    }

    @Override
    public void run() {
        super.run();
        try {
            while (updaterFlag) {
                LCD.clear(0);
                LCD.drawString(String.valueOf((float) ((Battery.getVoltage() * 10 + 0.5) / 10.0)), 13, 0);
                LCD.clear(1);
                LCD.drawString("L:" + leftMotor.getTachoCount() + " R:" + rightMotor.getTachoCount() + " C:" + centerMotor.getTachoCount(), 1, 1);
                LCD.clear(2);
                LCD.drawString("L:" + leftMotor.getSpeed() + " R:" + rightMotor.getSpeed() + " C:" + centerMotor.getSpeed(), 1, 2);
                LCD.clear(3);
                LCD.drawString("ColorName:" + colorSensor.getColorName((int) colorSensor.getColorID()), 1, 3);
                LCD.clear(4);
                LCD.drawString("USonic :" + ultrasonicSensor.getValue(), 1, 4);
                LCD.clear(5);
                LCD.drawString("Gyro   :" + gyroSensor.getValue(), 1, 5);
                LCD.clear(6);
                LCD.drawString("Touch  :" + touchSensor.getValue(), 1, 6);
                LCD.clear(7);
                LCD.drawString("Timer  :" + stopwatch.elapsed(), 1, 7);
                LCD.refresh();
                // 1分後にマシンを停止させる
                if (stopwatch.elapsed() >= 60000 && stopwatchFlag) {
                    Sound.twoBeeps();
                    System.exit(0);
                }
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopUpdaterFlag() {
        updaterFlag = false;
    }

    public void setStopwatchFlag(boolean flg) {
        stopwatch.reset();
        stopwatchFlag = flg;
    }
}
