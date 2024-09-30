package jumper.game.Screen.actor;

import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

public class ActorPositionManager {
    private final int screenWidth;
    private final int screenHeight;

    private final int sizeWidth;
    private final int sizeHeight;


    private final int curX;
    private final int interval = 20;

    private final ArrayList<Actor> buttonList = new ArrayList<>();

    public ActorPositionManager(int screenWidth, int screenHeight, int sizeWidth, int sizeHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.sizeWidth = sizeWidth;
        this.sizeHeight = sizeHeight;
        this.curX = (screenWidth - sizeWidth) / 2;
    }


    public void addActor(Actor actor) {
        actor.setSize(this.sizeWidth, this.sizeHeight);
        buttonList.add(actor);
        adjustButton();
    }

    private void adjustButton() {
        int actorNumber = buttonList.size();
        int totalHeight = actorNumber * sizeHeight + (actorNumber - 1) * interval;
        int curY = screenHeight - (screenHeight - totalHeight) / 2;
        for (Actor actor : buttonList) {
            actor.setPosition(curX, curY);
            curY -= (interval + sizeHeight);
            if (actorNumber == 4) {
                System.out.println(curY);
            }
        }
    }
}
