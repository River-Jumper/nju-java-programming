package jumper.game.network;


import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import jumper.game.Screen.GameScreen;
import lombok.extern.log4j.Log4j2;
import network.FrameState;

@Log4j2
public class ClientListener implements Listener {
    private final GameScreen gameScreen;

    public ClientListener(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof FrameState) {
            log.info("receive frame");
            gameScreen.frameStateAtomicReference.set((FrameState) object);
            //gameScreen.frameBuffer.addFrame((FrameState) object);
        }
    }
}
