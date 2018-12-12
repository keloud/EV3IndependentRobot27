package info.keloud.tec.ev3lejos.action;

interface ImplementUtil {
    // 保管した最大速度を返す
    float getMaxSpeed();

    // 最大速度を保管する
    void setMaxSpeed(float speed);

    // 距離(cm)を返す
    float getDistance();

    // 距離(cm)を保管する
    void setDistance(float distance);

    // 角度を返す
    float getAngle();

    // 角度を保管
    void setAngle(float angle);

    // モーター回転角を返す
    float getMotorAngle();

    // モーター回転角を保管
    void setMotorAngle(float distance);

    // カラーIDを返す
    int getColorId();

    // カラーIDを保管する
    void setColorId(int colorId);
}
