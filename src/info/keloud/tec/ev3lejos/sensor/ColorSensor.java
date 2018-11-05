package info.keloud.tec.ev3lejos.sensor;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

public class ColorSensor {
    private EV3ColorSensor color;
    private String modeName;

    public ColorSensor() {
        color = new EV3ColorSensor(SensorPort.S1);
    }

    // NONE, BLACK, BLUE, GREEN, YELLOW, RED, WHITE, BROWN
    public float[] getColorID() {
        float[] colorValue = new float[color.getColorIDMode().sampleSize()];
        color.getColorIDMode().fetchSample(colorValue, 0);
        modeName = color.getName();
        return colorValue;
    }

    public float[] getRedValue() {
        float[] colorValue = new float[color.getRedMode().sampleSize()];
        color.getRedMode().fetchSample(colorValue, 0);
        modeName = color.getName();
        return colorValue;
    }

    public float[] getRGBValue() {
        float[] colorValue = new float[color.getRGBMode().sampleSize()];
        color.getRGBMode().fetchSample(colorValue, 0);
        modeName = color.getName();
        return colorValue;
    }

    public String getModeName() {
        return modeName;
    }

    public String getColorName(int colorId) {
        switch (colorId) {
            case Color.NONE:
                return "NONE";
            case Color.BLACK:
                return "BLACK";
            case Color.BLUE:
                return "BLUE";
            case Color.GREEN:
                return "GREEN";
            case Color.YELLOW:
                return "YELLOW";
            case Color.RED:
                return "RED";
            case Color.WHITE:
                return "WHITE";
            case Color.BROWN:
                return "BROWN";
            default:
                return "NONE";
        }
    }
}
