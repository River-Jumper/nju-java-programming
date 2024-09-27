package jumper.game.network;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import jumper.game.gamelogic.manager.InputManager;
import network.KeyboardState;
import network.MouseState;

import java.awt.image.Kernel;

public class SeverListener implements Listener {
    private final InputManager inputManager;
    public SeverListener(InputManager inputManager) {
        this.inputManager = inputManager;
    }
    @Override
    public void connected(Connection connection) {

    }
    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof KeyboardState || object instanceof MouseState) {
            inputManager.updateState(object, connection.getID());
        }
    }
    @Override
    public void disconnected(Connection connection) {

    }
}
