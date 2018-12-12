package info.keloud.tec.ev3lejos.action;

import lejos.robotics.Color;

public abstract class AbstractUtil implements ImplementUtil {
    // 最大速度
    private float maxSpeed = 500;
    // 距離(cm)
    private float distance = 0;
    // 角度
    private float angle = 0;
    // モーター回転角
    private float motorAngle = 0;
    // タイヤの直径(cm)
    private float diameter = 5.6F;
    // カラーID
    private int colorId = 0;

    @Override
    public float getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public void setMaxSpeed(float speed) {
        this.maxSpeed = speed;
    }

    @Override
    public float getDistance() {
        return distance;
    }

    @Override
    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public float getAngle() {
        return angle;
    }

    @Override
    public void setAngle(float angle) {
        this.angle = angle;
    }

    @Override
    public float getMotorAngle() {
        return motorAngle;
    }

    // 距離(cm)をモーター回転角に変換して保管する
    @Override
    public void setMotorAngle(float distance) {
        setDistance(distance);
        this.motorAngle = getDistance() / (getTireCircumference() / 360);
    }

    @Override
    public int getColorId() {
        return colorId;
    }

    @Override
    public void setColorId(int colorId) {
        this.colorId = colorId;
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

    // タイヤの円周を返す
    private float getTireCircumference() {
        return (float) (diameter * Math.PI);
    }

    // 車体の回転円周を返す
    private float getMachineCircumference() {
        // 車体の幅(cm)
        float width = 8.9F;
        return (float) (width * Math.PI);
    }

    // 角度からマシン回転距離(cm)に変換する
    float angle2Distance(float angle) {
        setAngle(angle);
        return getMachineCircumference() / (360 / getAngle());
    }

    // 距離(cm)をタコカウントに変換する
    float distance2Cumulative(float distance) {
        setDistance(distance);
        return (getDistance() / diameter / (float) Math.PI) * 360;
    }

    // タコカウントから距離(cm)に変換する
    float cumulative2Distance(float cumulative) {
        return (cumulative * diameter * (float) Math.PI) / 360;
    }
}