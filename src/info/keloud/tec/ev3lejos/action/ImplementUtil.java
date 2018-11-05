package info.keloud.tec.ev3lejos.action;

interface ImplementUtil {
    void run();

    float getMaxSpeed();

    void setMaxSpeed(float speed);

    float getDistance();

    void setDistance(float distance);

    float getAngle();

    void setAngle(float angle);

    int getColorId();

    void setColorId(int colorId);
}
