package jumper.game.network;

import com.esotericsoftware.kryonet.Server;
import jumper.game.gamelogic.manager.InputManager;
import jumper.game.gamelogic.system.SystemContext;
import network.Network;

import java.io.IOException;

public class GameServer {
    public Server server;
    public GameServer() throws IOException {
        server = new Server();
        Network.register(server);
        server.bind(Network.port);
        server.start();
    }
    public void setListener(SystemContext context) {
        server.addListener(new SeverListener(context));
    }
}
