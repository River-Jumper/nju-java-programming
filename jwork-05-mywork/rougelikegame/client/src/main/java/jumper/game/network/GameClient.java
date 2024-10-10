package jumper.game.network;

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


        String localHost = "127.0.0.1";
        String serverHost = "192.168.1.121";

        client.connect(500000, localHost, Network.port);
    }

    public static void main(String[] args) throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost().getHostAddress());
    }

}
