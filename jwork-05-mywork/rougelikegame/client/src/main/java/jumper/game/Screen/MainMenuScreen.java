
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
                dispose();
                GameScreen gameScreen = new GameScreen(game);


                ServerStart serverStart = new ServerStart();
                try {
                    serverStart.start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                try {
                    GameClient gameClient = new GameClient();
                    gameClient.client.addListener(new ClientListener(gameScreen));
                    gameScreen.setGameClient(gameClient);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                game.setScreen(gameScreen);
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
                System.out.println("Haha, you foolish");
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
