package org.example.gamelogic.system;

import com.badlogic.gdx.math.Circle;
import dev.dominion.ecs.api.Dominion;
import dev.dominion.ecs.api.Entity;
import dev.dominion.ecs.api.Results;
import org.example.gamelogic.component.BulletComponent;
import org.example.gamelogic.component.CollisionComponent;
import org.example.gamelogic.component.PlayerComponent;
import org.example.gamelogic.component.PositionComponent;

import java.util.HashSet;

public class CollisionDetectionSystem implements Runnable {
    private final Dominion world;

    public CollisionDetectionSystem(Dominion world) {
        this.world = world;
    }

    @Override
    public void run() {
        Results<Results.With2<PositionComponent, CollisionComponent>> entities = world
                .findEntitiesWith(PositionComponent.class, CollisionComponent.class);

        for (Results.With2<PositionComponent, CollisionComponent> result : entities) {
            processEntity(result.entity(), entities);
        }

    }

    protected void processEntity(Entity entity,
                                 Results<Results.With2<PositionComponent, CollisionComponent>> results) {

        //在每一帧开始前将每个碰撞实体的碰撞列表清空，重新检测
        entity.get(CollisionComponent.class).collisionEntities.clear();

        for (Results.With2<PositionComponent, CollisionComponent> otherResult : results) {
            Entity otherEntity = otherResult.entity();
            if (otherEntity == entity) {
                continue;
            }
            else {
                PositionComponent position = entity.get(PositionComponent.class);
                PositionComponent otherPositionComponent = otherEntity.get(PositionComponent.class);
                CollisionComponent collisionComponent = entity.get(CollisionComponent.class);
                CollisionComponent otherCollisionComponent = otherEntity.get(CollisionComponent.class);

                //circle collision detection
                Circle thisCircle = new Circle(position.x, position.y, collisionComponent.radius);
                Circle otherCircle = new Circle(otherPositionComponent.x, otherPositionComponent.y, collisionComponent.radius);

                if (thisCircle.overlaps(otherCircle)) {
                    collisionComponent.collisionEntities.add(otherEntity);
                }
            }
        }
    }


}
