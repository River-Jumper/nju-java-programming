package org.example.gamelogic.component;

import com.badlogic.ashley.core.Component;

public class RandomMoveComponent implements Component {
    public float maxInterval;
    public float currentInterval;

    public RandomMoveComponent(float maxInterval) {
        this.maxInterval = maxInterval;
        this.currentInterval = maxInterval;
    }
    public RandomMoveComponent(float maxInterval, float currentInterval) {
        this.maxInterval = maxInterval;
        this.currentInterval = currentInterval;
    }
}
