package jumper.game;

import com.badlogic.gdx.Game;
import jumper.game.Screen.JoinServerScreen;
import jumper.game.Screen.MainMenuScreen;
import jumper.game.Screen.TestScreen;
import jumper.game.network.GameClient;

import java.io.IOException;

public class MyGame extends Game {
    @Override
    public void create() {
        setScreen(new MainMenuScreen(this));
    }
}
