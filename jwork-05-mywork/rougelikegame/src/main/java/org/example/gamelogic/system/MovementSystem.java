package org.example.gamelogic.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import org.example.FinalClass;
import org.example.gamelogic.component.CollisionComponent;
import org.example.gamelogic.component.MovableComponent;
import org.example.gamelogic.component.PositionComponent;

import java.lang.reflect.Field;


public class MovementSystem extends IteratingSystem {

    public MovementSystem() {
        super(Family.all(MovableComponent.class, PositionComponent.class, CollisionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MovableComponent movableComponent = entity.getComponent(MovableComponent.class);
        PositionComponent position = entity.getComponent(PositionComponent.class);
        int radius = entity.getComponent(CollisionComponent.class).radius;

        position.x += deltaTime * movableComponent.xSpeed;
        position.y += deltaTime * movableComponent.ySpeed;

        edgeDetection(position, radius);
    }

    private void edgeDetection(PositionComponent position, int radius) {
        if (position.x < 0) {
            position.x = 0;
        }
        if (position.y < 0) {
            position.y = 0;
        }
        if (position.x > FinalClass.WIDTH - 2 * radius) {
            position.x = FinalClass.WIDTH - 2 * radius;
        }
        if (position.y > FinalClass.HEIGHT - 2 * radius) {
            position.y = FinalClass.HEIGHT - 2 * radius;
        }
    }

}
