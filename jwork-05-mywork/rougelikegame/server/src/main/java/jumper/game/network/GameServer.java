package jumper.game.network;

import com.esotericsoftware.kryonet.Server;
import jumper.game.gamelogic.manager.InputManager;
import network.Network;

import java.io.IOException;

public class GameServer {
    Server server;
    public GameServer(InputManager inputManager) throws IOException {
        server = new Server();
        Network.register(server);
        server.bind(Network.port);
        server.addListener(new SeverListener(inputManager));

        server.start();
    }
}
