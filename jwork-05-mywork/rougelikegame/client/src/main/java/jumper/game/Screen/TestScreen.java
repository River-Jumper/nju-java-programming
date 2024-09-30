package jumper.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import jumper.game.DesktopLauncher;
import jumper.game.Screen.actor.ActorPositionManager;

public class TestScreen extends MenuScreen{
    public TestScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        Skin skin = new Skin(Gdx.files.internal("skin/comic-ui.json"));
        ActorPositionManager buttonManager = new ActorPositionManager(DesktopLauncher.WIDTH, DesktopLauncher.HEIGHT, 80, 25);

        TextButton testButton = new TextButton("Test", skin);
        //MenuScreen screen = new TestScreen(game);
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


        buttonManager.addActor(testButton);

        stage.addActor(testButton);
    }
}
