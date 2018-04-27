package info.keloud.tec.ev3lejos.manager;

import info.keloud.tec.ev3lejos.sensor.ColorSensor;
import info.keloud.tec.ev3lejos.sensor.GyroSensor;
import info.keloud.tec.ev3lejos.sensor.UltrasonicSensor;
import lejos.hardware.Battery;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.utility.Stopwatch;

import java.awt.*;

public class SensorUpdater extends Thread {
    ColorSensor colorSensor;
    UltrasonicSensor ultrasonicSensor;
    GyroSensor gyroSensor;
    Stopwatch stopwatch;

    boolean stopFlag;

    public SensorUpdater(ColorSensor colorSensor, UltrasonicSensor ultrasonicSensor, GyroSensor gyroSensor) {
        this.colorSensor = colorSensor;
        this.ultrasonicSensor = ultrasonicSensor;
        this.gyroSensor = gyroSensor;
        this.stopwatch = new Stopwatch();
        stopFlag = true;
    }

    @Override
    public void run() {
        int flg = 0;
        super.run();
        try {
            while (stopFlag) {
                LCD.clear(0);
                LCD.drawString(String.valueOf((float) ((Battery.getVoltage() * 10 + 0.5) / 10.0)), 15, 0);
                LCD.clear(1);
                //LCD.drawString("L:" + accumulationleftMotor + " R:" + accumulationrightMotor + " C:" + accumulationcenterMotor, 1, 1);
                LCD.clear(2);
                LCD.drawString("ColorId:" + colorSensor.getValue(), 1, 2);
                LCD.clear(3);
                LCD.drawString("USonic:" + ultrasonicSensor.getValue(), 1, 3);
                LCD.clear(4);
                LCD.drawString("Gyro:" + gyroSensor.getValue(), 1, 4);
                LCD.clear(7);
                LCD.drawString("Timer:" + stopwatch.elapsed(), 1, 7);
                LCD.refresh();
                //1分後にマシンを停止させる
                if(stopwatch.elapsed() >= 60000){
                    Sound.twoBeeps();
                    System.exit(0);
                }
                Thread.sleep(500);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setStop() {
        stopFlag = true;
    }
}
