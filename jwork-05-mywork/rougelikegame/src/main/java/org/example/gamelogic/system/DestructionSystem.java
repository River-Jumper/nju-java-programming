package org.example.gamelogic.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import org.example.gamelogic.component.DestructionComponent;

public class DestructionSystem extends IteratingSystem {
    public DestructionSystem() {
        super(Family.all(DestructionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
