package org.example.gamelogic.component;


import dev.dominion.ecs.api.Entity;
import dev.dominion.ecs.api.Results;

import java.util.HashSet;
import java.util.Set;

public class CollisionComponent{
    public int radius;
    public HashSet<Entity> collisionEntities = new HashSet<>();

    public CollisionComponent(int radius) {
        this.radius = radius;
    }
}
