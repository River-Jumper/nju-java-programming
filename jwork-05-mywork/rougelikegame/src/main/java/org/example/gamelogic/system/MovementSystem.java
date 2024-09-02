package org.example.gamelogic.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import org.example.gamelogic.component.MovableComponent;
import org.example.gamelogic.component.PositionComponent;

public class MovementSystem extends IteratingSystem {

    public MovementSystem() {
        super(Family.all(MovableComponent.class, PositionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MovableComponent movableComponent = entity.getComponent(MovableComponent.class);
        PositionComponent positionComponent = entity.getComponent(PositionComponent.class);

        positionComponent.x += deltaTime * movableComponent.xSpeed;
        positionComponent.y += deltaTime * movableComponent.ySpeed;
    }
}
