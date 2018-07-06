package info.keloud.tec.ev3lejos;

import info.keloud.tec.ev3lejos.manager.SensorUpdater;
import info.keloud.tec.ev3lejos.sensor.ColorSensor;
import info.keloud.tec.ev3lejos.sensor.GyroSensor;
import info.keloud.tec.ev3lejos.sensor.TouchSensor;
import info.keloud.tec.ev3lejos.sensor.UltrasonicSensor;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Main {
    public static ColorSensor colorSensor;
    public static UltrasonicSensor ultrasonicSensor;
    public static GyroSensor gyroSensor;
    public static TouchSensor touchSensor;
    public static EV3MediumRegulatedMotor centerMotor;
    public static EV3LargeRegulatedMotor leftMotor;
    public static EV3LargeRegulatedMotor rightMotor;
    public static SensorUpdater sensorUpdater;

    public static void main(String[] args) {
        // ディスプレイ案内開始
        LCD.clear(6);
        LCD.drawString("Init ColorSensor", 1, 6);
        LCD.refresh();
        // カラーセンサーの初期化
        colorSensor = new ColorSensor();

        // ディスプレイ案内更新
        LCD.clear(6);
        LCD.drawString("Init UltrasonicSensor", 1, 6);
        LCD.refresh();
        // 超音波センサーの初期化
        ultrasonicSensor = new UltrasonicSensor();

        // ディスプレイ案内の更新
        LCD.clear(6);
        LCD.drawString("Init GyroSensor", 1, 6);
        LCD.refresh();
        // ジャイロセンサーの初期化
        gyroSensor = new GyroSensor();

        // ディスプレイ案内の更新
        LCD.clear(6);
        LCD.drawString("Init TouchSensor", 1, 6);
        LCD.refresh();
        // タッチセンサーの初期化
        touchSensor = new TouchSensor();

        // ディスプレイ案内の更新
        LCD.clear(6);
        LCD.drawString("Init Motor", 1, 6);
        LCD.refresh();
        // モーターの初期
        centerMotor = new EV3MediumRegulatedMotor(MotorPort.A);
        leftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
        rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
        leftMotor.synchronizeWith(new EV3LargeRegulatedMotor[]{rightMotor});

        // ディスプレイ案内の更新
        LCD.clear(6);
        LCD.drawString("Init Thread", 1, 6);
        LCD.refresh();
        //センサ――と画面更新用スレッドの生成
        sensorUpdater = new SensorUpdater();
        sensorUpdater.start();

        //ディスプレイ案内の更新
        LCD.clear(6);
        LCD.drawString("Init Complete", 1, 6);
        LCD.refresh();
        //初期化完了を知らせる
        Sound.beep();

        //EV3クラスを生成する
        EV3 ev3 = new EV3();
        ev3.run();
    }
}
