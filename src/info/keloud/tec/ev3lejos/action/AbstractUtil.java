package info.keloud.tec.ev3lejos.action;

public abstract class AbstractUtil implements ImplementUtil {
    // Diameter of tire(cm)
    protected float diameter = 5.6F;
    // Width of wheel(cm)
    protected float width = 8.85F;
    // Max Speed
    protected float maxSpeed = 500;
    // Minimum Spped
    protected float minSpeed = 100;
    // Current Speed
    protected float currentSpeed = 500;
    // Distance
    protected float distance = 0;
    // Angle
    protected float angle = 0;
    // Color
    protected int colorId = 0;

    @Override
    public void run() {
    }

    @Override
    public void setSpeed(float speed) {
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
    public float getCumulative(float distance) {
        //指定した距離に必要なタコカウント
        return (distance / diameter / (float) Math.PI) * 360;
    }
}
