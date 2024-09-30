package jumper.game.Screen.actor.listener;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SwitchMenuScreen extends ClickListener {
//    private final MenuScreen curScreen;
//    private final MenuScreen nextScreen;
//    public SwitchMenuScreen(MenuScreen curScreen, MenuScreen nextScreen) {
//        super();
//        this.curScreen = curScreen;
//        this.nextScreen = nextScreen;
//    }
    @Override
    public void clicked(InputEvent event, float x, float y) {
        System.out.println("no ha ha");
        //curScreen.switchScreen(nextScreen);
    }
}
