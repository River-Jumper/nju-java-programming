package org.example.gamelogic.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import org.example.gamelogic.component.BulletComponent;
import org.example.gamelogic.component.CollisionComponent;
import org.example.gamelogic.component.DestructionComponent;
import org.example.gamelogic.component.PlayerComponent;

import java.util.Set;

public class BulletCollisionSystem extends IteratingSystem {
    public BulletCollisionSystem() {
        super(Family.all(BulletComponent.class, CollisionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        CollisionComponent collisionComponent = entity.getComponent(CollisionComponent.class);
        Set<Entity> collisionSet = collisionComponent.collisionEntities;
        for (Entity collisionEntity : collisionSet) {
            if (collisionEntity.getComponent(PlayerComponent.class) == null) {
                entity.add(new DestructionComponent());
                break;
            }
        }
    }
}
