package info.keloud.tec.ev3lejos.sensor;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

public class TouchSensor {
    EV3TouchSensor touch;
    private float[] touchValue;

    public TouchSensor() {
        touch = new EV3TouchSensor(SensorPort.S4);
        touchValue = new float[touch.getTouchMode().sampleSize()];
    }

    public float getValue() {
        touch.getTouchMode().fetchSample(touchValue, 0);
        return touchValue[0];
    }
}
