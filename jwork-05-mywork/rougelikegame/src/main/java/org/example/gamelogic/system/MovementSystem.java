package org.example.gamelogic.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import org.example.GameConfig;
import org.example.gamelogic.component.*;


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

        if (edgeDetection(position, radius) && entity.getComponent(BulletComponent.class) != null) {
            entity.add(new DestructionComponent());
        }
    }

    private boolean edgeDetection(PositionComponent position, int radius) {
        boolean hitEdge = false;
        if (position.x < 0) {
            position.x = 0;
            hitEdge = true;
        }
        if (position.y < 0) {
            position.y = 0;
            hitEdge = true;
        }
        if (position.x > GameConfig.WIDTH - 2 * radius) {
            position.x = GameConfig.WIDTH - 2 * radius;
            hitEdge = true;
        }
        if (position.y > GameConfig.HEIGHT - 2 * radius) {
            position.y = GameConfig.HEIGHT - 2 * radius;
            hitEdge = true;
        }

        return hitEdge;
    }

}
