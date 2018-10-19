package info.keloud.tec.ev3lejos.action;

import lejos.hardware.Sound;

import static info.keloud.tec.ev3lejos.Main.leftMotor;
import static info.keloud.tec.ev3lejos.Main.rightMotor;

public abstract class AbstractUtil implements ImplementUtil {
    // Max Speed
    float maxSpeed = 500;
    // Minimum Spped
    float minSpeed = 100;
    // Current Speed
    float currentSpeed = 500;
    // Distance
    float distance = 0;
    // Angle
    float angle = 0;
    // Diameter of tire(cm)
    private float diameter = 5.6F;
    // Width of wheel(cm)
    private float width = 8.85F;
    // Color
    private int colorId = 0;

    @Override
    public void run() {
    }

    @Override
    public void setMaxSpeed(float speed) {
        this.maxSpeed = speed;
    }

    @Override
    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public void setAngle(float angle) {
        this.angle = angle;
    }

    @Override
    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public void setColorId(String colorId) {
        switch (colorId) {
            case "BLACK":
                setColorId(7);
                break;
            case "BLUE":
                setColorId(6);
                break;
            case "GREEN":
                //
                break;
            case "YELLOW":
                //
                break;
            case "RED":
                setColorId(0);
                break;
            case "WHITE":
                setColorId(6);
                break;
            case "BROWN":
                //
                break;
            default:
                //
                break;
        }
    }

    //centimeter単位で指定
    //タコカウント用に変換する
    float distance2Cumulative(float distance) {
        //指定した距離に必要なタコカウント
        return (distance / diameter / (float) Math.PI) * 360;
    }

    //角度から距離に変換する
    float angle2Distance(float angle) {
        return (angle * width * (float) Math.PI) / 360;
    }

    //角度からタコカウント用に変換する
    float angle2Cumulative(float angle) {
        return distance2Cumulative(angle2Distance(angle));
    }

    void stopLargeMotor() {
        Sound.beepSequenceUp();
        // 停止
        leftMotor.startSynchronization();
        leftMotor.stop(true);
        rightMotor.stop(true);
        leftMotor.endSynchronization();
    }
}
