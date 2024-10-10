package jumper.game.render;

import com.badlogic.gdx.graphics.Texture;
import network.FrameState;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private final Map<FrameState.Symbol, Texture> table;
    public SymbolTable() {
        this.table = new HashMap<>();
    }
    public void put(FrameState.Symbol symbol, Texture texture) {
        table.put(symbol, texture);
    }
    public Texture getTexture(FrameState.Symbol symbol) {
        return table.get(symbol);
    }
}
