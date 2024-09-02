package org.example.gamelogic.component;

import com.badlogic.ashley.core.Component;

public class CollisionComponent implements Component {
    public int radius;

    public CollisionComponent(int radius) {
        this.radius = radius;
    }
}
