package jumper.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class MyScreen extends ScreenAdapter {
    protected final Game game;
    protected Stage stage;
    protected Image backgroundImage;

    public MyScreen(Game game) {
        this.game = game;
        stageConfig();
    }

    protected void stageConfig() {
        this.stage = new Stage();

        //process mouse input
        Gdx.input.setInputProcessor(stage);

        //background
        Texture backgroundTexture = new Texture(Gdx.files.internal("figures/background/horizontal/MainMenu.png"));
        backgroundImage = new Image(backgroundTexture);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        stage.addActor(backgroundImage);
    }

    protected void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
