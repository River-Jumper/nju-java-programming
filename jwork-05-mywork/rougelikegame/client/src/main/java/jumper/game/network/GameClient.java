package jumper.game.network;

import com.badlogic.gdx.Screen;
import jumper.game.Screen.MyScreen;
import network.Network;
import com.esotericsoftware.kryonet.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class GameClient {
    public Client client;
    public GameClient() throws IOException {
        this.client = new Client();
        Network.register(this.client);
        this.client.start();
    }

    public void connect(String ip) throws IOException {
        client.connect(500000, ip, Network.port);
    }

    public static void main(String[] args) throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost().getHostAddress());
    }

}
