package jumper.game.gamelogic.system.collision;

import dev.dominion.ecs.api.Entity;
import jumper.game.gamelogic.component.MovableComponent;
import jumper.game.gamelogic.component.PositionComponent;
import jumper.game.gamelogic.component.singleton.CollisionEventManager;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;

import static jumper.game.SingletonManager.SingletonName.COLLISION_MESSAGE;

@RequiredArgsConstructor
public class CollisionMoveSystem implements Runnable {
    private final SystemContext context;


    @Override
    public void run() {
        CollisionEventManager collisionEventManager = context.singletonManager().getSingleton(COLLISION_MESSAGE);

        var collisionEvents = collisionEventManager.getEventWith(MovableComponent.class, PositionComponent.class);

        while (collisionEvents != null  && !collisionEvents.isEmpty()) {
            var event = collisionEvents.poll();
            //deal with speed
            dealWithSpeed(event.entity1().get(MovableComponent.class), event.entity2().get(MovableComponent.class));
        }
    }

    //一个最简单的碰撞时速度处理方案
    private void dealWithSpeed(MovableComponent move) {
        move.xSpeed = 0;
        move.ySpeed = 0;
    }
    //Opposite speeds and lose to 1/2
    private void dealWithSpeed(MovableComponent movement1, MovableComponent movement2) {
        movement1.xSpeed *= -((float) 1 /2);
        movement1.ySpeed *= -((float) 1 /2);
        movement2.xSpeed *= -((float) 1 /2);
        movement2.ySpeed *= -((float) 1 /2);
    }
    //仅仅在速度方向上做阻挠
    private void dealWithSpeed(Entity thisEntity, Entity otherEntity) {
        PositionComponent thisPosition = thisEntity.get(PositionComponent.class);
        PositionComponent otherPosition = otherEntity.get(PositionComponent.class);
        MovableComponent movement = thisEntity.get(MovableComponent.class);

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
