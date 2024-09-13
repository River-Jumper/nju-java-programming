package jumper.game.gamelogic.component.collision;


import dev.dominion.ecs.api.Entity;

import java.util.HashSet;

public class CollisionComponent{
    public int radius;
    public HashSet<Entity> collisionEntities = new HashSet<>();

    public CollisionComponent(int radius) {
        this.radius = radius;
    }
}
