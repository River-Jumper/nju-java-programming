package jumper.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class DesktopLauncher {
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 720;
    public static void main (String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("emoji-war");
        config.setWindowedMode(WIDTH, HEIGHT);
        config.useVsync(true);
        config.setForegroundFPS(60);

        log.info("start game");
        new Lwjgl3Application(new MyGame(), config);
    }
}
