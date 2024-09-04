package org.example.gamelogic.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import org.example.gamelogic.component.DestructionComponent;
import org.example.gamelogic.component.HealthComponent;

public class HealthSystem extends IteratingSystem {
    public HealthSystem() {
        super(Family.all(HealthComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HealthComponent healthComponent = entity.getComponent(HealthComponent.class);
        if (healthComponent.currentHealth > healthComponent.maxHealth) {
            healthComponent.currentHealth = healthComponent.maxHealth;
        }
        if (healthComponent.currentHealth <= 0) {
            entity.add(new DestructionComponent());
        }
    }
}
