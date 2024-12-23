package jumper.game.network;

import com.esotericsoftware.kryonet.Server;
import jumper.game.gamelogic.manager.InputManager;
import dev.dominion.ecs.api.Scheduler;
import jumper.game.gamelogic.system.SystemContext;
import network.Network;
import network.Start;


import java.io.IOException;

public class GameServer {
    public Server server;
    public int maxPlayerNum = 1;
    public GameServer() throws IOException {
        server = new Server();
        Network.register(server);
        server.bind(Network.port);
        server.start();
    }
    public void setListener(SystemContext context, Scheduler scheduler) {
        server.addListener(new SeverListener(context, scheduler, this));
    }
}
