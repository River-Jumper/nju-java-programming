package jumper.game.network;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import dev.dominion.ecs.api.Scheduler;
import jumper.game.gamelogic.factory.PlayerFactory;
import jumper.game.gamelogic.manager.InputManager;
import jumper.game.gamelogic.system.SystemContext;
import lombok.extern.log4j.Log4j2;
import network.*;

import java.awt.image.Kernel;

@Log4j2
public class SeverListener implements Listener {
    private final SystemContext context;
    private final Scheduler scheduler;
    private final GameServer gameServer;
    private int curPlayerNum = 0;
    public SeverListener(SystemContext context, Scheduler scheduler, GameServer gameServer) {
        this.context = context;
        this.scheduler = scheduler;
        this.gameServer = gameServer;
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
        if (object instanceof PauseControl) {
            if (((PauseControl) object).isPause()) {
                scheduler.tickAtFixedRate(0);
            }
            else {
                scheduler.tickAtFixedRate(60);
            }
        }
        if (object instanceof Start) {
            this.curPlayerNum++;
            if (this.curPlayerNum == this.gameServer.maxPlayerNum) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                this.gameServer.server.sendToAllTCP(new Start());
                scheduler.tickAtFixedRate(60);
            }
        }
        if (object instanceof PlayerNum) {
            this.gameServer.maxPlayerNum = ((PlayerNum) object).playerNum();
        }
    }
}
