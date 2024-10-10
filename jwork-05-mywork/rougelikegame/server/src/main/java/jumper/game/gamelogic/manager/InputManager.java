package jumper.game.gamelogic.manager;

import lombok.extern.log4j.Log4j2;
import network.KeyboardState;
import network.MouseState;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

//不知道map的并发会不会出问题，希望不会
@Log4j2
public class InputManager {
    private final Map<Integer, AtomicReference<MouseState>> mouseStateMap = new HashMap<>();
    private final Map<Integer, AtomicReference<KeyboardState>> keyboardStateMap = new HashMap<>();

    public void updateState(Object state, int clientID) {
        if (state instanceof MouseState) {
            log.debug("receive mouseState: {}, {}, {}",
                    ((MouseState) state).isClick(),
                    ((MouseState) state).x(),
                    ((MouseState) state).y());
            if (mouseStateMap.get(clientID) != null) {
                mouseStateMap.get(clientID).set((MouseState) state);
            }
            else {
                AtomicReference<MouseState> mouseStateAtomicReference = new AtomicReference<>();
                mouseStateAtomicReference.set((MouseState) state);
                mouseStateMap.put(clientID, mouseStateAtomicReference);
            }
        } else if (state instanceof KeyboardState) {
            if (keyboardStateMap.get(clientID) != null) {
                keyboardStateMap.get(clientID).set((KeyboardState) state);
            }
            else {
                AtomicReference<KeyboardState> keyboardStateAtomicReference = new AtomicReference<>();
                keyboardStateAtomicReference.set((KeyboardState) state);
                keyboardStateMap.put(clientID, keyboardStateAtomicReference);
            }
        }
    }
    public MouseState getMouseState(int clientID) {
        return mouseStateMap.get(clientID).get();
    }
    public KeyboardState getKeyboardState(int clientID) {
        return keyboardStateMap.get(clientID).get();
    }
}
