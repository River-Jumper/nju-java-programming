package jumper.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MultiPlayersGameScreen extends MenuScreen {

    public MultiPlayersGameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {

        //button
        Skin skin = new Skin(Gdx.files.internal("skin/comic-ui.json"));

        TextButton createServerButton = new TextButton("Create Server", skin);
        createServerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new CreateServerScreen(game));
            }
        });

        TextButton joinServerButton = new TextButton("Join Server", skin);
        joinServerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new JoinServerScreen(game));
            }
        });

        TextButton returnButton = new TextButton("Return", skin);
        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        });

        Table table = new Table();
        table.setFillParent(true);

        table.add(createServerButton).width(80).pad(10);
        table.row();
        table.add(joinServerButton).width(80).pad(10);
        table.row();
        table.add(returnButton).width(80).pad(10);


        //actor in stage
        stage.addActor(table);
    }

}
