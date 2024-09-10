package org.example.gamelogic.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import org.example.GameConfig;
import org.example.gamelogic.component.MovableComponent;
import org.example.gamelogic.component.PositionComponent;
import org.example.gamelogic.component.TargetComponent;
import org.example.gamelogic.component.TargetMoveComponent;

public class TargetMoveSystem extends IteratingSystem {
    public TargetMoveSystem() {
        super(Family.all(TargetMoveComponent.class, MovableComponent.class, PositionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        PositionComponent position = entity.getComponent(PositionComponent.class);
        MovableComponent movement = entity.getComponent(MovableComponent.class);
        TargetMoveComponent targetMove = entity.getComponent(TargetMoveComponent.class);

        ImmutableArray<Entity> targetEntities = getEngine()
                .getEntitiesFor(Family.all(TargetComponent.class, PositionComponent.class).get());

        //the closest target in target set
        PositionComponent closestPosition = null;
        float closestDistance = Float.MAX_VALUE;
        for (Entity targetEntity : targetEntities) {
            PositionComponent thisPosition = targetEntity.getComponent(PositionComponent.class);
            if (closestPosition == null) {
                closestPosition = thisPosition;
            }
            float thisDistance = getDistance(position, thisPosition);
            if (thisDistance < closestDistance) {
                closestDistance = thisDistance;
                closestPosition = thisPosition;
            }
        }
        if (closestPosition != null && closestDistance <= targetMove.targetDistance) {
            float deltaX = closestPosition.x - position.x;
            float deltaY = closestPosition.y - position.y;
            //closestDistance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            float sinX = deltaX / closestDistance;
            float cosX = deltaY / closestDistance;


            float speedX = ((deltaX / closestDistance) * GameConfig.EnemyMAXSPEED);
            float speedY = ((deltaY / closestDistance) * GameConfig.EnemyMAXSPEED);

            movement.xSpeed = speedX;
            movement.ySpeed = speedY;
        }


    }

    private float getDistance(PositionComponent positionA, PositionComponent positionB) {
        float deltaX = positionB.x - positionA.x;
        float deltaY = positionB.y - positionA.y;

        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
