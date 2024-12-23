package jumper.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import jumper.game.ServerStart;
import jumper.game.network.ClientListener;
import jumper.game.network.GameClient;
import jumper.game.playback.ClearPlaybackRecord;
import lombok.extern.log4j.Log4j2;
import network.Network;
import network.PlayerNum;
import network.Start;

import java.io.IOException;

@Log4j2
public class CreateServerScreen extends MyScreen {
    public CreateServerScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        Skin skin = new Skin(Gdx.files.internal("skin/comic-ui.json"));

        TextField portText = new TextField("Port: ", skin);

        TextField playerNumText = new TextField("Player number: ", skin);

        TextButton createButton = new TextButton("Create", skin);
        createButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int port = Network.port;
                if (!portText.getText().replaceAll("Port: ", "").isEmpty()) {
                    port = Integer.parseInt(portText.getText().replaceAll("Port: ", ""));
                    assert port > 0;
                }
                int playerNum = Integer.parseInt(playerNumText.getText().replaceAll("Player number: ", ""));
                assert playerNum >= 1;
                //log.info("port = {}, playerNum = {}", port, playerNum);

                //prepare: create gameScreen
                dispose();
                GameScreen gameScreen = new GameScreen(game);

                try {
                    //prepare: create gameClient
                    GameClient gameClient = new GameClient();
                    gameClient.client.addListener(new ClientListener(gameScreen, gameClient));
                    //prepare: prepare server
                    ServerStart serverStart = new ServerStart();
                    serverStart.start();
                    //connect
                    gameClient.connect("127.0.0.1");
                    gameClient.client.sendTCP(new PlayerNum(playerNum));
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

        table.add(portText).width(150).pad(10);
        table.row().center();
        table.add(playerNumText).width(150).pad(10);
        table.row().center();
        table.add(createButton).width(80).pad(10);
        table.row().center();
        table.add(returnButton).width(80).pad(10);

        stage.addActor(table);
    }
}
