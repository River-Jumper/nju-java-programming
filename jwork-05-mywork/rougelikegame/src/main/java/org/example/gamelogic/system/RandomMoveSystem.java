package org.example.gamelogic.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import org.example.GameConfig;
import org.example.gamelogic.component.MovableComponent;
import org.example.gamelogic.component.RandomMoveComponent;

import java.util.Random;

public class RandomMoveSystem extends IteratingSystem {
    public RandomMoveSystem() {
        super(Family.all(RandomMoveComponent.class, MovableComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RandomMoveComponent randomMove = entity.getComponent(RandomMoveComponent.class);
        if (randomMove.currentInterval > 0) {
            randomMove.currentInterval -= deltaTime;
        }
        else {
            Random random = new Random();
            float config = (float) 2 / 3;
            float speedX = (2 * random.nextInt(GameConfig.EnemyMAXSPEED) - GameConfig.EnemyMAXSPEED) * config;
            float speedY = (2 * random.nextInt(GameConfig.EnemyMAXSPEED) - GameConfig.EnemyMAXSPEED) * config;

            MovableComponent movement = entity.getComponent(MovableComponent.class);
            movement.xSpeed = speedX;
            movement.ySpeed = speedY;

            randomMove.currentInterval = randomMove.maxInterval;
        }


    }
}
