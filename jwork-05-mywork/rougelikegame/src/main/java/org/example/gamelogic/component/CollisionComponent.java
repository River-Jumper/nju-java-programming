package org.example.gamelogic.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.HashSet;
import java.util.Set;

public class CollisionComponent implements Component {
    public int radius;
    public Set<Entity> collisionEntities = new HashSet<>();

    public CollisionComponent(int radius) {
        this.radius = radius;
    }
}
