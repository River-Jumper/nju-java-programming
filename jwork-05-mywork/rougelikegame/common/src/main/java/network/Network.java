package network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class Network {
    static public int port = 54126;
    static public void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(FrameState.class);
        kryo.register(KeyboardState.class);
        kryo.register(MouseState.class);
    }
}
