
package jumper.game;


import com.esotericsoftware.kryonet.Server;
import jumper.game.gamelogic.manager.InputManager;
import jumper.game.gamelogic.manager.SingletonManager;
import jumper.game.gamelogic.system.SystemContext;
import jumper.game.network.GameServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //manager
        SingletonManager singletonManager = new SingletonManager();
        InputManager inputManager = new InputManager();
        //system context
        //SystemContext systemContext = new SystemContext()

        //server
        GameServer gameServer = new GameServer(inputManager);
    }
}


