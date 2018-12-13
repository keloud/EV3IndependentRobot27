package info.keloud.tec.ev3lejos.action;

import lejos.robotics.Color;

abstract class AbstractUtil {
    // タイヤの直径(cm)
    private float diameter = 5.6F;

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

    // 距離(cm)をモーター回転角に変換して保管する
    float getMotorAngle(float distance) {
        return distance / (getTireCircumference() / 360);
    }

    // 文字列からカラーIDを保管する
    // 一致しない場合はNONEを保管する
    int getColorId(String colorId) {
        switch (colorId) {
            case "NONE":
                return Color.NONE;
            case "BLACK":
                return Color.BLACK;
            case "BLUE":
                return Color.BLUE;
            case "GREEN":
                return Color.GREEN;
            case "YELLOW":
                return Color.YELLOW;
            case "RED":
                return Color.RED;
            case "WHITE":
                return Color.WHITE;
            case "BROWN":
                return Color.BROWN;
            default:
                return Color.NONE;
        }
    }

    // 角度からマシン回転距離(cm)に変換する
    float angle2Distance(float angle) {
        return getMachineCircumference() / (360 / angle);
    }

    // 距離(cm)をタコカウントに変換する
    float distance2Cumulative(float distance) {
        return (distance / diameter / (float) Math.PI) * 360;
    }

    // タコカウントから距離(cm)に変換する
    float cumulative2Distance(float cumulative) {
        return (cumulative * diameter * (float) Math.PI) / 360;
    }
}