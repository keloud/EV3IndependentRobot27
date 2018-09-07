package info.keloud.tec.ev3lejos.sensor;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class ColorSensor {
    private EV3ColorSensor color;

    public ColorSensor() {
        color = new EV3ColorSensor(SensorPort.S1);

    }

    // NONE, BLACK, BLUE, GREEN, YELLOW, RED, WHITE, BROWN
    public float getColorID() {
        float[] colorValue = new float[color.getColorIDMode().sampleSize()];
        color.getColorIDMode().fetchSample(colorValue, 0);
        return colorValue[0];
    }

    public float getRedValue() {
        float[] colorValue = new float[color.getRedMode().sampleSize()];
        color.getRedMode().fetchSample(colorValue, 0);
        return colorValue[0];
    }
}
