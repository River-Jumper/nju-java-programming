package jumper.game.network;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import jumper.game.gamelogic.factory.PlayerFactory;
import jumper.game.gamelogic.manager.InputManager;
import jumper.game.gamelogic.system.SystemContext;
import lombok.extern.log4j.Log4j2;
import network.KeyboardState;
import network.MouseState;

import java.awt.image.Kernel;

@Log4j2
public class SeverListener implements Listener {
    private final SystemContext context;
    public SeverListener(SystemContext context) {
        this.context = context;
    }
    @Override
    public void connected(Connection connection) {
        log.info("server connect client:{}", connection.getID());
        PlayerFactory.make(context.world(), connection.getID());
    }
    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof KeyboardState || object instanceof MouseState) {
            context.inputManager().updateState(object, connection.getID());
        }
    }
}
