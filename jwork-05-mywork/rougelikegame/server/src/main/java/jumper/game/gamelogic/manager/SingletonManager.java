package jumper.game.gamelogic.manager;


import java.util.EnumMap;

public class SingletonManager {
    public enum SingletonName {
        PAUSE, GAME_TIME, COLLISION_MESSAGE;
    }
    private final EnumMap<SingletonName, Object> singletonMap = new EnumMap<>(SingletonName.class);

    public void addSingletonComponent(SingletonName name, Object component) {
        singletonMap.put(name, component);
    }

    public <T> T getSingleton(SingletonName name) {
        return (T)singletonMap.get(name);
    }
}
