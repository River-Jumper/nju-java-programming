package jumper.game.Screen.actor.listener;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import jumper.game.Screen.MenuScreen;

public class TestListener extends ClickListener {
//    private MenuScreen screen;
    public TestListener(MenuScreen screen) {
//        this.screen = screen;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        System.out.println("haha");
    }
}
