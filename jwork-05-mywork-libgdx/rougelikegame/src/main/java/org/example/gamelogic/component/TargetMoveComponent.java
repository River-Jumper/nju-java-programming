package org.example.gamelogic.component;

import com.badlogic.ashley.core.Component;

public class TargetMoveComponent implements Component {
    public int targetX;
    public int targetY;

    //if distance between this entity and target <= targetDistance, this entity will
    //move towards target
    public int targetDistance;

    public TargetMoveComponent(int targetDistance) {
        this.targetDistance = targetDistance;
    }
}
