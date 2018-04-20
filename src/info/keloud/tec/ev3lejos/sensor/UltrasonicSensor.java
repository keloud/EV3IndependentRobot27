package info.keloud.tec.ev3lejos.sensor;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class UltrasonicSensor {
    EV3UltrasonicSensor ultrasonic;
    private float[] ultrasonicValue;

    public UltrasonicSensor() {
        ultrasonic = new EV3UltrasonicSensor(SensorPort.S2);
        ultrasonicValue = new float[ultrasonic.getDistanceMode().sampleSize()];
    }

    public float getValue() {
        ultrasonic.getDistanceMode().fetchSample(ultrasonicValue, 0);
        return ultrasonicValue[0];
    }
}
