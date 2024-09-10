package org.example.gamelogic.system;

import dev.dominion.ecs.api.Composition;
import dev.dominion.ecs.api.Dominion;
import dev.dominion.ecs.api.Entity;
import dev.dominion.ecs.api.Results;
import org.example.gamelogic.component.*;

import java.util.HashSet;
import java.util.Set;

public class BulletCollisionSystem implements Runnable {
    private final Dominion world;

    public BulletCollisionSystem(Dominion world) {
        this.world = world;
    }

    @Override
    public void run() {
        Results<Results.With3<PositionComponent, CollisionComponent, BulletComponent>> results = world
                .findEntitiesWith(PositionComponent.class, CollisionComponent.class, BulletComponent.class);

        for (Results.With3<PositionComponent, CollisionComponent, BulletComponent> result : results) {
            Entity entity = result.entity();
            processEntity(entity);
        }
    }

    protected void processEntity(Entity entity) {
        CollisionComponent collisionComponent = entity.get(CollisionComponent.class);
        HashSet<Entity> collisionSet = collisionComponent.collisionEntities;

        for (Entity collisionEntity : collisionSet) {
            if (collisionEntity.get(PlayerComponent.class) == null) {
                entity.add(new DestructionComponent());
                break;
            }
        }

    }
}
