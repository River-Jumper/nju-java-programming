package jumper.game.network;


import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import network.FrameState;

public class ClientListener implements Listener {
    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof FrameState) {

        }
    }
}
