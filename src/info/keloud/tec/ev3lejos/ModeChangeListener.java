package info.keloud.tec.ev3lejos;

public class ModeChangeListener implements ModeChangeListenerInterface {
    private ModeChangeListenerInterface listenerInterface;
    private String modeName;

    //リスナーを追加する
    public void setListener(ModeChangeListenerInterface listenerInterface) {
        this.listenerInterface = listenerInterface;
    }

    //リスナーを削除する
    public void removeListener() {
        this.listenerInterface = null;
    }

    //動作名を渡す
    public String getModeName() {
        return modeName;
    }

    //動作名を受け取る
    public void setModeName(String modeName) {
        if (listenerInterface != null) {
            if (!this.modeName.equals(modeName)) {
                this.modeName = modeName;
                onModeChanged();
            }
        }
    }

    @Override
    public void onModeChanged() {
    }
}
