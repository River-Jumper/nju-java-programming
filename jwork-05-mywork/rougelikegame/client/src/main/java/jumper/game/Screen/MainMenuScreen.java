
package jumper.game.Screen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import jumper.game.DesktopLauncher;
import jumper.game.Screen.actor.ActorPositionManager;

public class MainMenuScreen extends MenuScreen {

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
                System.out.println("Haha, you foolish");
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

        TextButton settingsButton = new TextButton("Settings", skin);


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
