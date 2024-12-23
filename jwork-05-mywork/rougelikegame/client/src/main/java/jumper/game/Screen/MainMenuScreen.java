
package jumper.game.Screen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import jumper.game.ServerStart;
import jumper.game.network.ClientListener;
import jumper.game.network.GameClient;
import jumper.game.playback.ClearPlaybackRecord;
import network.Network;
import network.Start;

import java.io.IOException;


public class MainMenuScreen extends MyScreen {

    public MainMenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {

        //button
        Skin skin = new Skin(Gdx.files.internal("skin/comic-ui.json"));

        TextButton singlePlayerGameButton = new TextButton("Single Player Game", skin);
        singlePlayerGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
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

        TextButton multiPlayersGameButton = new TextButton("Multi Players Game", skin);
        multiPlayersGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MultiPlayersGameScreen(game));
            }
        });

        TextButton playBackButton = new TextButton("Playback", skin);
        playBackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new PlaybackScreen(game));
            }
        });

        TextButton settingsButton = new TextButton("Settings", skin);
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Haha, you foolish");
            }
        });


        Table table = new Table();
        table.setFillParent(true);

        table.add(singlePlayerGameButton).width(80).pad(10);
        table.row().center();
        table.add(multiPlayersGameButton).width(80).pad(10);
        table.row().center();
        table.add(playBackButton).width(80).pad(10);
        table.row().center();
        table.add(settingsButton).width(80).pad(10);


        //actor in stage
        stage.addActor(table);
    }

/*
    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
    */
}
