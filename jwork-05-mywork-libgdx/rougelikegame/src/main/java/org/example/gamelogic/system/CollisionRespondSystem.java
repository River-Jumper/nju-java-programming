package org.example.gamelogic.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import org.example.gamelogic.component.*;

import java.util.Set;

//碰撞对于实体速度的影响
public class CollisionRespondSystem extends IteratingSystem {
    public CollisionRespondSystem() {
        super(Family.all(MovableComponent.class, CollisionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CollisionComponent collisionComponent = entity.getComponent(CollisionComponent.class);
        Set<Entity> collisionSet = collisionComponent.collisionEntities;
        for (Entity collisionEntity : collisionSet) {
            dealWithSpeed(entity, collisionEntity);
        }
    }
    //一个最简单的碰撞时速度处理方案
    private void dealWithSpeed(MovableComponent move) {
        move.xSpeed = 0;
        move.ySpeed = 0;
    }
    //仅仅在速度方向上做阻挠
    private void dealWithSpeed(Entity thisEntity, Entity otherEntity) {
        PositionComponent thisPosition = thisEntity.getComponent(PositionComponent.class);
        PositionComponent otherPosition = otherEntity.getComponent(PositionComponent.class);
        MovableComponent movement = thisEntity.getComponent(MovableComponent.class);

        float deltaX = otherPosition.x - thisPosition.x;
        float deltaY = otherPosition.y - thisPosition.y;
        float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        float sin = deltaY / distance;
        float cos = deltaX / distance;

        float speedRemain = movement.xSpeed / sin + movement.ySpeed / cos;

        float newSpeedX = speedRemain * sin;
        float newSpeedY = speedRemain * cos;

        movement.xSpeed = newSpeedX;
        movement.ySpeed = newSpeedY;
    }
}
