package info.keloud.tec.ev3lejos.sensor;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class ColorSensor {
    private EV3ColorSensor color;
    private float[] colorValue;

    public ColorSensor() {
        color = new EV3ColorSensor(SensorPort.S1);
        colorValue = new float[color.getColorIDMode().sampleSize()];
    }

    // NONE, BLACK, BLUE, GREEN, YELLOW, RED, WHITE, BROWN
    public float getValue() {
        color.getRedMode().fetchSample(colorValue, 0);
        return colorValue[0];
    }
}
