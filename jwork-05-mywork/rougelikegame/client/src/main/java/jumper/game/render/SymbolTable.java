package jumper.game.render;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import network.FrameState;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<FrameState.Symbol, Image> table;
    public SymbolTable() {
        //emoji
        Texture ghostTexture = new Texture("src/main/resources/emoji/ghost.png");
        Texture jokerTexture = new Texture("src/main/resources/emoji/joker.png");
        Texture angryTexture = new Texture("src/main/resources/emoji/angry.png");
        //bullet
        Texture flowerTexture = new Texture("src/main/resources/bullet/flower.png");
        Texture snowTexture = new Texture("src/main/resources/bullet/snow.png");

        this.table = new HashMap<>();
        table.put(FrameState.Symbol.PLAYER, new Image(jokerTexture));
        table.put(FrameState.Symbol.ENEMY, new Image(ghostTexture));
        table.put(FrameState.Symbol.BULLET, new Image(flowerTexture));
    }
}
