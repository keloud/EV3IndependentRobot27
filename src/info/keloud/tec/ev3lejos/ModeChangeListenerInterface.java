package info.keloud.tec.ev3lejos;

import java.util.EventListener;

public interface ModeChangeListenerInterface extends EventListener {
    //動作モードの変更を通知する
    void onModeChanged();
}
