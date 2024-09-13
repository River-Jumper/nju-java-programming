package jumper.game.gamelogic.manager;

import network.KeyboardState;
import network.MouseState;

import java.util.HashMap;
import java.util.Map;


public class InputManager {
    private final Map<Integer, MouseState> mouseStateMap = new HashMap<>();
    private final Map<Integer, KeyboardState> keyboardStateMap = new HashMap<>();

    public void updateState(Object state, int clientID) {
        if (state instanceof MouseState) {
            mouseStateMap.put(clientID, (MouseState) state);
        } else if (state instanceof KeyboardState) {
            keyboardStateMap.put(clientID, (KeyboardState) state);
        }
    }
    public MouseState getMouseState(int clientID) {
        return mouseStateMap.get(clientID);
    }
    public KeyboardState getKeyboardState(int clientID) {
        return keyboardStateMap.get(clientID);
    }
}
