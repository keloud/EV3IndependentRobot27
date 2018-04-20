package info.keloud.tec.ev3lejos.sensor;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;

public class GyroSensor {
    EV3GyroSensor gyro;
    private float[] gyroValue;

    public GyroSensor() {
        gyro = new EV3GyroSensor(SensorPort.S3);
        gyroValue = new float[gyro.getAngleMode().sampleSize()];
    }

    public float getValue() {
        gyro.getAngleMode().fetchSample(gyroValue, 0);
        return gyroValue[0];
    }

    public void initGyro() {
        gyro.reset();
    }
}