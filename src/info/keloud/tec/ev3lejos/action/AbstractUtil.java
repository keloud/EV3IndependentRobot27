package info.keloud.tec.ev3lejos.action;

import lejos.robotics.Color;

import static info.keloud.tec.ev3lejos.Main.leftMotor;
import static info.keloud.tec.ev3lejos.Main.rightMotor;

public abstract class AbstractUtil implements ImplementUtil {
    // Max Speed
    private float maxSpeed = 500;
    // Current Speed
    private float currentSpeed = 0;
    // Distance
    private float distance = 0;
    // Angle
    private float angle = 0;
    // MotorAngle
    private float motorAngle = 0;
    // Diameter of tire(cm)
    private float diameter = 5.6F;
    // Width of wheel(cm)
    private float width = 8.9F;
    // Color
    private int colorId = 0;

    @Override
    public void setMaxSpeed(float speed) {
        this.maxSpeed = speed;
    }

    @Override
    public float getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public float getDistance() {
        return distance;
    }

    @Override
    public void setAngle(float angle) {
        this.angle = angle;
    }

    @Override
    public float getAngle() {
        return angle;
    }

    @Override
    public float getMotorAngle() {
        return motorAngle;
    }

    @Override
    public void setMotorAngle(float distance) {
        setDistance(distance);
        this.motorAngle = getDistance() / (getTireCircumference() / 360);
    }

    @Override
    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    @Override
    public int getColorId() {
        return colorId;
    }

    // 文字列からカラーIDを保管する
    // 一致しない場合はNONEを保管する
    void setColorId(String colorId) {
        switch (colorId) {
            case "NONE":
                setColorId(Color.NONE);
                break;
            case "BLACK":
                setColorId(Color.BLACK);
                break;
            case "BLUE":
                setColorId(Color.BLUE);
                break;
            case "GREEN":
                setColorId(Color.GREEN);
                break;
            case "YELLOW":
                setColorId(Color.YELLOW);
                break;
            case "RED":
                setColorId(Color.RED);
                break;
            case "WHITE":
                setColorId(Color.WHITE);
                break;
            case "BROWN":
                setColorId(Color.BROWN);
                break;
            default:
                setColorId(Color.NONE);
                break;
        }
    }

    // 現在の速度を返す
    float getCurrentSpeed() {
        return currentSpeed;
    }

    // 現在の速度を保管する
    void setCurrentSpeed(float currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    //centimeter単位で指定
    // 距離をタコカウントに変換する
    float distance2Cumulative(float distance) {
        setDistance(distance);
        return (getDistance() / diameter / (float) Math.PI) * 360;
    }

    // 角度からマシン回転距離に変換する
    float angle2Distance(float angle) {
        setAngle(angle);
        return getMachineCircumference() / (360 / getAngle());
    }

    // タコカウントから距離を求める
    float cumulative2Distance(float cumulative) {
        return (cumulative * diameter * (float) Math.PI) / 360;
    }

    // タイヤの円周を返す
    float getTireCircumference() {
        return (float) (diameter * Math.PI);
    }

    // タイヤ駆動部を止める
    void stopLargeMotor() {
        // 停止
        leftMotor.startSynchronization();
        leftMotor.stop();
        rightMotor.stop();
        leftMotor.endSynchronization();
    }

    // 車体の回転円周を返す
    float getMachineCircumference() {
        return (float) (width * Math.PI);
    }

    // 動作を止める時用音楽
    void playStopSound() {
        //Sound.beepSequenceUp();
    }

    // タイヤを動かす
    void moveLargeMotor(boolean direction) {
        if (direction) {
            leftMotor.startSynchronization();
            leftMotor.forward();
            rightMotor.forward();
            leftMotor.endSynchronization();
        } else {
            leftMotor.startSynchronization();
            leftMotor.backward();
            rightMotor.backward();
            leftMotor.endSynchronization();
        }
    }
}