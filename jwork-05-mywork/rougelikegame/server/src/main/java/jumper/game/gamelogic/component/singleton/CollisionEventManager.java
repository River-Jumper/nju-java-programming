package jumper.game.gamelogic.component.singleton;

import dev.dominion.ecs.api.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Queue;

public class CollisionEventManager {
    private final Queue<CollisionEvent> events = new ArrayDeque<>();

    public void addEvent(Entity entity1, Entity entity2) {
        events.add(new CollisionEvent(entity1, entity2));
    }

    @Nullable
    public CollisionEvent pollEvent() {
        return events.poll();
    }

    public void clearEvent() {
        events.clear();
    }

    @Nullable
    public Queue<CollisionEvent> getEventWith(Class<?>... classes) {
        Queue<CollisionEvent> eventsWith2 = new ArrayDeque<>();
        for (CollisionEvent event : events) {
            Entity entity1 = event.entity1();
            Entity entity2 = event.entity2();

            boolean entity1HasClass = true;
            boolean entity2HasClass = true;

            for (Class<?> clazz : classes) {
                entity1HasClass &= entity1.has(clazz);
                entity2HasClass &= entity2.has(clazz);
            }

            //one of entities has this class
            if (entity1HasClass || entity2HasClass) {
                eventsWith2.add(event);
            }
        }
        return eventsWith2;
    }

    public record CollisionEvent(Entity entity1, Entity entity2) {
    }
}
