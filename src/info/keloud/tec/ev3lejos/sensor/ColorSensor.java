package info.keloud.tec.ev3lejos.sensor;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

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

    public float[] getRGBValue() {
        float[] colorValue = new float[color.getRGBMode().sampleSize()];
        color.getRGBMode().fetchSample(colorValue, 0);
        return colorValue;
    }

    public int getColorId(float red, float green, float blue) {
        float sum = red + green + blue;
        float redRatio = red / sum;
        float greenRatio = green / sum;
        float blueRatio = blue / sum;

        if (redRatio < 0.3 && greenRatio < 0.4 && blueRatio > 0.5) return Color.BLUE;
        if (redRatio < 0.2 && greenRatio > 0.5 && blueRatio > 0.2) return Color.GREEN;
        if (redRatio > 0.5 && greenRatio > 0.3 && blueRatio < 0.1) return Color.YELLOW;
        if (redRatio > 0.7 && greenRatio < 0.2 && blueRatio < 0.2) return Color.RED;

        return Color.NONE;
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
