package jumper.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import jumper.game.ServerStart;
import jumper.game.network.ClientListener;
import jumper.game.network.GameClient;
import jumper.game.playback.ClearPlaybackRecord;
import network.Start;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;


public class JoinServerScreen extends MyScreen {
    public JoinServerScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        //client

        //button
        Skin skin = new Skin(Gdx.files.internal("skin/comic-ui.json"));

        TextField ipText = new TextField("Ip: ", skin);
        TextField portText = new TextField("Port: ", skin);

        TextButton confirmButton = new TextButton("Confirm", skin);
        confirmButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String ip = ipText.getText().replaceAll("Ip: ", "");
                String port = portText.getText().replaceAll("Port: ", "");

                DealWithIpAndPort dealWithIpAndPort = new DealWithIpAndPort(ip, port);
                ip = dealWithIpAndPort.getIp();
                assert ip != null;

                //prepare: create gameScreen
                dispose();
                GameScreen gameScreen = new GameScreen(game);

                try {
                    //prepare: create gameClient
                    GameClient gameClient = new GameClient();
                    gameClient.client.addListener(new ClientListener(gameScreen, gameClient));
                    //connect
                    gameClient.connect(ip);
                    // first delete playback record
                    ClearPlaybackRecord.clear();
                    // then change screen to GameScreen
                    game.setScreen(gameScreen);
                    //start server
                    gameClient.client.sendTCP(new Start());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        TextButton returnButton = new TextButton("Return", skin);
        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MultiPlayersGameScreen(game));
            }
        });

        Table table = new Table();
        table.setFillParent(true);

        table.add(ipText).width(150).pad(10);
        table.row().center();
        table.add(portText).width(150).pad(10);
        table.row().center();
        table.add(confirmButton).width(100).pad(10);
        table.row().center();
        table.add(returnButton).width(100).pad(10);
        table.row().center();

        stage.addActor(table);
    }

    private static class DealWithIpAndPort {
        private String ip;
        private String port;
        public DealWithIpAndPort(String ipText, String portText) {
            ip = ipText.replaceAll("Ip: ", "");
            port = portText.replaceAll("Port: ", "");
        }
        public boolean isLegal() {
            // 3 point in ip
            char targetChar = '.';
            int count = (int)this.ip.chars()
                    .filter(c -> c == targetChar)
                    .count();
            // 4 nums in ip <= 255, and is int
            boolean ipNumCheck = true;
            for (String num : this.ip.split("\\.")) {
                if (Integer.parseInt(num) < 0 || Integer.parseInt(num) >= 255) {
                    ipNumCheck = false;
                    break;
                }
            }
            // port is an int
            boolean portNumCheck = true;
            if (Integer.parseInt(this.ip) < 0) {
                portNumCheck = false;
            }
            return count == 3 & ipNumCheck & portNumCheck;
        }
        @Nullable
        public String getIp() {
            if (!isLegal()) {
                return null;
            }
            return ip;
        }
        @Nullable
        public String getPort() {
            if (!isLegal()) {
                return null;
            }
            return port;
        }
    }
}
