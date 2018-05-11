package info.keloud.tec.ev3lejos.manager;

import info.keloud.tec.ev3lejos.sensor.ColorSensor;
import info.keloud.tec.ev3lejos.sensor.GyroSensor;
import info.keloud.tec.ev3lejos.sensor.UltrasonicSensor;
import lejos.hardware.Battery;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.utility.Stopwatch;

public class SensorUpdater extends Thread {
    private ColorSensor colorSensor;
    private UltrasonicSensor ultrasonicSensor;
    private GyroSensor gyroSensor;
    private Stopwatch stopwatch;

    private boolean updaterFlag;
    private boolean stopwatchFlag;

    public SensorUpdater(ColorSensor colorSensor, UltrasonicSensor ultrasonicSensor, GyroSensor gyroSensor) {
        this.colorSensor = colorSensor;
        this.ultrasonicSensor = ultrasonicSensor;
        this.gyroSensor = gyroSensor;
        this.stopwatch = new Stopwatch();
    }

    @Override
    public void run() {
        super.run();
        updaterFlag = true;
        stopwatchFlag = false;
        try {
            while (updaterFlag) {
                LCD.clear(0);
                LCD.drawString(String.valueOf((float) ((Battery.getVoltage() * 10 + 0.5) / 10.0)), 13, 0);
                LCD.clear(1);
                //LCD.drawString("L:" + accumulationleftMotor + " R:" + accumulationrightMotor + " C:" + accumulationcenterMotor, 1, 1);
                LCD.clear(2);
                LCD.drawString("ColorId:" + colorSensor.getValue(), 1, 2);
                LCD.clear(3);
                LCD.drawString("USonic :" + ultrasonicSensor.getValue(), 1, 3);
                LCD.clear(4);
                LCD.drawString("Gyro   :" + gyroSensor.getValue(), 1, 4);
                LCD.clear(7);
                LCD.drawString("Timer  :" + stopwatch.elapsed(), 1, 7);
                LCD.refresh();
                //1分後にマシンを停止させる
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
        stopwatchFlag = flg;
    }
}
