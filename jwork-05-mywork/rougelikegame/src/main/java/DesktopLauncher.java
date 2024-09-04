import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.example.FinalClass;


public class DesktopLauncher {
    public static void main (String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("emoji-war");
        config.setWindowedMode(FinalClass.WIDTH, FinalClass.HEIGHT);
        config.useVsync(true);
        config.setForegroundFPS(60);

        new Lwjgl3Application(new MyGame(), config);
    }
}
