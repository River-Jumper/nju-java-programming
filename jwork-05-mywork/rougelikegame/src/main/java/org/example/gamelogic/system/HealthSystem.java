package org.example.gamelogic.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class HealthSystem extends IteratingSystem {
    public HealthSystem(Family family) {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float v) {

    }
}
