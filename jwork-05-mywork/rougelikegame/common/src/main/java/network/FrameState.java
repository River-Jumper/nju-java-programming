package network;

import java.util.HashMap;
import java.util.Map;

public class FrameState {
    public enum Symbol {
        ENEMY,
        PLAYER,
        BULLET,
    }

    public Map<Position, Thing> frame = new HashMap<>();

    public record Position(float x, float y) {}
    public record Thing(Symbol symbol, int radius) {}
}
