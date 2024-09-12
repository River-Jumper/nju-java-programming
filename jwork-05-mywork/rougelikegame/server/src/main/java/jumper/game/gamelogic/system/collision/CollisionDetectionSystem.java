package jumper.game.gamelogic.system.collision;

import dev.dominion.ecs.api.Entity;
import lombok.RequiredArgsConstructor;


import java.util.HashSet;
import java.util.Set;


@RequiredArgsConstructor
public class CollisionDetectionSystem implements Runnable {
    private final SystemContext context;

    @Override
    public void run() {
        Set<Entity> collidedEntities = new HashSet<>();
        var collisionResults = context.world().findEntitiesWith(PositionComponent.class, CollisionComponent.class);

        for (var result : collisionResults) {
            float x = result.comp1().x;
            float y = result.comp1().y;
            int radius = result.comp2().radius;
            Circle thisCircle = new Circle(x, y, radius);

            for (var other : collisionResults) {
                if (result != other && !collidedEntities.contains(other.entity())) {
                    float otherX = other.comp1().x;
                    float otherY = other.comp1().y;
                    int otherRadius = other.comp2().radius;
                    Circle otherCircle = new Circle(otherX, otherY, otherRadius);
                    if (thisCircle.overlaps(otherCircle)) {
                        CollisionEventManager collisionEventManager = context.singletonManager().getSingleton(COLLISION_MESSAGE);
                        collisionEventManager.addEvent(result.entity(), other.entity());
                    }
                }
            }
            collidedEntities.add(result.entity());
        }
    }
}
