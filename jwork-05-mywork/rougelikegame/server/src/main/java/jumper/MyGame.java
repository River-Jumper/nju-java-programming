import com.badlogic.gdx.Game;
import org.example.screen.GameScreen;

public class MyGame extends Game {
    @Override
    public void create() {
        setScreen(new GameScreen(this));
    }
}
