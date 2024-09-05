package org.example.gamelogic.component;

import com.badlogic.ashley.core.Component;

public class ShootingComponent implements Component {
    public float shootInterval;
    public float currentInterval;
    public ShootingComponent(float shootInterval, float currentInterval) {
        this.shootInterval = shootInterval;
        this.currentInterval = currentInterval;
    }
}
