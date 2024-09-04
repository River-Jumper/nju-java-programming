package org.example.gamelogic.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import org.example.gamelogic.component.AttackableComponent;
import org.example.gamelogic.component.CollisionComponent;
import org.example.gamelogic.component.HealthComponent;

import java.util.Set;

public class DamageSystem extends IteratingSystem {
    public DamageSystem(Family family) {
        super(Family.all(CollisionComponent.class, HealthComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CollisionComponent collisionComponent = entity.getComponent(CollisionComponent.class);
        Set<Entity> collisionSet = collisionComponent.collisionEntities;
        if (!collisionSet.isEmpty()) {
            HealthComponent health = entity.getComponent(HealthComponent.class);
            for (Entity collisionEntity : collisionSet) {
                AttackableComponent attackableComponent = collisionEntity
                        .getComponent(AttackableComponent.class);
                if (attackableComponent != null) {
                    health.currentHealth -= attackableComponent.attack;
                }
            }
        }
    }
}
