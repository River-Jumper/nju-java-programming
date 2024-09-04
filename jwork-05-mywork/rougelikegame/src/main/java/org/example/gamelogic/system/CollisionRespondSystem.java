package org.example.gamelogic.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import org.example.gamelogic.component.AttackableComponent;
import org.example.gamelogic.component.CollisionComponent;
import org.example.gamelogic.component.HealthComponent;
import org.example.gamelogic.component.MovableComponent;

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
        if (!collisionSet.isEmpty()) {
            MovableComponent move = entity.getComponent(MovableComponent.class);

            dealWithSpeed(move);
        }
    }
    //一个最简单的碰撞时速度处理方案
    private void dealWithSpeed(MovableComponent move) {
        move.xSpeed = 0;
        move.ySpeed = 0;
    }
    //后续可以加上冲量的影响
}
