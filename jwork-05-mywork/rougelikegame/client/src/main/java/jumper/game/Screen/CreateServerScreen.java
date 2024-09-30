package jumper.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class CreateServerScreen extends MenuScreen{
    public CreateServerScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        Skin skin = new Skin(Gdx.files.internal("skin/comic-ui.json"));

        TextField portText = new TextField("Port: ", skin);
        TextButton createButton = new TextButton("Create", skin);
        createButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String port = portText.getText().replaceAll("Port: ", "");
                System.out.println(port);
            }
        });

        Table table = new Table();
        table.setFillParent(true);

        table.add(portText).width(150).pad(10);
        table.row().center();
        table.add(createButton).width(80).pad(10);

        stage.addActor(table);
    }
}
