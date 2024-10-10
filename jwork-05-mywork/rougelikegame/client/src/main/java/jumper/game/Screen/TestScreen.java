package jumper.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class TestScreen extends MyScreen {
    private Image ghostImage;
    private float x;
    private float y;

    public TestScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        Skin skin = new Skin(Gdx.files.internal("skin/comic-ui.json"));

        TextButton testButton = new TextButton("Test", skin);
        //MyScreen screen = new TestScreen(game);
        //testButton.addListener(new TestListener(screen));
        //testButton.addListener(new SwitchMenuScreen());
        //testButton.addListener(new SwitchMenuScreen(this, new MainMenuScreen(game)));

        testButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        });

        Texture ghostTexture = new Texture("figures/emoji/ghost.png");
        ghostImage = new Image(ghostTexture);
        x = 10;
        y = 10;
        ghostImage.setPosition(x, y);
        ghostImage.setSize(30, 30);

        stage.addActor(testButton);
        stage.addActor(ghostImage);
    }
    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update();
        stage.draw();
    }

    private void update() {
        x += 1;
        y += 1;
        ghostImage.setPosition(x, y);
    }
}
