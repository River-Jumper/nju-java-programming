package org.example.gamelogic.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Circle;
import org.example.gamelogic.component.CollisionComponent;
import org.example.gamelogic.component.PositionComponent;

public class CollisionDetectionSystem extends IteratingSystem {
    public CollisionDetectionSystem() {
        super(Family.all(PositionComponent.class, CollisionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
        CollisionComponent collisionComponent = entity.getComponent(CollisionComponent.class);
        //在每一帧开始前将每个碰撞实体的碰撞列表清空，重新检测
        collisionComponent.collisionEntities.clear();

        ImmutableArray<Entity> allCollisionEntities = getEngine()
                .getEntitiesFor(Family.all(PositionComponent.class, CollisionComponent.class).get());

        for (Entity otherEntity : allCollisionEntities) {
            if (otherEntity == entity) {
                continue;
            }
            else {
                PositionComponent otherPositionComponent = otherEntity
                        .getComponent(PositionComponent.class);
                CollisionComponent otherCollisionComponent = otherEntity
                        .getComponent(CollisionComponent.class);

                Circle thisCircle = new Circle(positionComponent.x, positionComponent.y, collisionComponent.radius);
                Circle otherCircle = new Circle(otherPositionComponent.x, otherPositionComponent.y, collisionComponent.radius);
                //碰撞检测的核心
                if (thisCircle.overlaps(otherCircle)) {
                    collisionComponent.collisionEntities.add(otherEntity);
                }
            }
        }


    }
}
