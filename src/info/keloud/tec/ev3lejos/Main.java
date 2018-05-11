package info.keloud.tec.ev3lejos;

import info.keloud.tec.ev3lejos.manager.SensorUpdater;
import info.keloud.tec.ev3lejos.motor.*;
import info.keloud.tec.ev3lejos.sensor.ColorSensor;
import info.keloud.tec.ev3lejos.sensor.GyroSensor;
import info.keloud.tec.ev3lejos.sensor.UltrasonicSensor;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Main {
    private static float maxSpeed;

    public static void main(String[] args) {
        // ディスプレイ案内開始
        LCD.clear();
        LCD.drawString("Init ColorSensor", 1, 5);
        LCD.refresh();
        // カラーセンサーの初期化
        ColorSensor colorSensor = new ColorSensor();
        // ディスプレイ案内更新
        LCD.clear(5);
        LCD.drawString("Init UltrasonicSensor", 1, 5);
        LCD.refresh();
        // 超音波センサーの初期化
        UltrasonicSensor ultrasonicSensor = new UltrasonicSensor();
        // ディスプレイ案内の更新
        LCD.clear(5);
        LCD.drawString("Init GyroSensor", 1, 5);
        LCD.refresh();
        // ジャイロセンサーの初期化
        GyroSensor gyroSensor = new GyroSensor();
        // ディスプレイ案内の更新
        LCD.clear(5);
        LCD.drawString("Init Motor", 1, 5);
        LCD.refresh();
        // モーターの初期化
        CenterTachoMotorPort centerTachoMotorPort = new CenterTachoMotorPort();
        CenterMotor centerMotor = new CenterMotor(centerTachoMotorPort);
        LeftTachoMotorPort leftTachoMotorPort = new LeftTachoMotorPort();
        LeftMotor leftMotor = new LeftMotor(leftTachoMotorPort);
        RightTachoMotorPort rightTachoMotorPort = new RightTachoMotorPort();
        RightMotor rightMotor = new RightMotor(rightTachoMotorPort);
        leftMotor.synchronizeWith(new RegulatedMotor[]{rightMotor});

        // ディスプレイ案内の更新
        LCD.clear(5);
        LCD.drawString("Init Thread", 1, 5);
        LCD.refresh();
        //センサ――と画面更新用スレッドの生成
        SensorUpdater sensorUpdater = new SensorUpdater(colorSensor, ultrasonicSensor, gyroSensor);
        sensorUpdater.run();

        LCD.clear(5);
        LCD.drawString("in Action", 1, 5);
        LCD.refresh();
        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        /*leftMotor.startSynchronization();
        leftMotor.setSpeed(500);
        rightMotor.setSpeed(500);
        leftMotor.forward();
        rightMotor.forward();
        Delay.msDelay(10000);
        leftMotor.endSynchronization();
        leftMotor.stop();
        rightMotor.stop();*/

        // Enterキーを押して次に進む
        Button.ENTER.waitForPress();
        sensorUpdater.setStop();
    }
}
