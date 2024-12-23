package jumper.game.network;


import com.badlogic.gdx.Game;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import jumper.game.Screen.GameScreen;
import jumper.game.Screen.MyScreen;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import network.FrameState;
import network.Start;

@Log4j2
public class ClientListener implements Listener {

    private final GameScreen gameScreen;
    private final GameClient gameClient;

    public ClientListener(GameScreen gameScreen, GameClient gameClient) {
        this.gameScreen = gameScreen;
        this.gameClient = gameClient;
    }

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof FrameState) {
            log.info("receive frame");
            if (this.gameScreen != null) {
                gameScreen.frameStateAtomicReference.set((FrameState) object);
            }
        }
        if (object instanceof Start) {
            this.gameScreen.setGameClient(this.gameClient);
        }
    }
}
