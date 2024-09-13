package jumper.game.gamelogic.system.move;

import jumper.game.gamelogic.component.move.MovableComponent;
import jumper.game.gamelogic.component.move.PositionComponent;
import jumper.game.gamelogic.component.move.TargetComponent;
import jumper.game.gamelogic.component.move.TargetMoveComponent;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class TargetMoveSystem implements Runnable {
    private SystemContext context;

    @Override
    public void run() {

        var targetResults = context.world().findCompositionsWith(TargetComponent.class, PositionComponent.class);

        context.world().findCompositionsWith(TargetMoveComponent.class, MovableComponent.class, PositionComponent.class)
                .forEach(result -> {
                    PositionComponent position = result.comp3();

                    //find the closest target
                    PositionComponent closestPosition = null;
                    float closestDistance = Float.MAX_VALUE;
                    for (var targetResult : targetResults) {
                        PositionComponent targetPosition = targetResult.comp2();
                        if (closestPosition == null) {
                            closestPosition = targetPosition;
                            closestDistance = getDistance(position, closestPosition);
                        }
                        else if (getDistance(position, closestPosition) < closestDistance) {
                            closestDistance = getDistance(position, closestPosition);
                            closestPosition = targetPosition;
                        }

                    }
                    //move towards the closest target
                    if (closestPosition != null && closestDistance <= result.comp1().targetDistance) {
                        dealWithSpeed(position, closestPosition, result.comp2());
                    }
                });
    }

    private void dealWithSpeed(PositionComponent position, PositionComponent closestPosition,
                               MovableComponent movement) {
        float deltaX = closestPosition.x - position.x;
        float deltaY = closestPosition.y - position.y;
        float closestDistance = getDistance(position, closestPosition);

        float speedX = ((deltaX / closestDistance) * movement.maxSpeedX);
        float speedY = ((deltaY / closestDistance) * movement.maxSpeedY);

        movement.xSpeed = speedX;
        movement.ySpeed = speedY;
    }

    private float getDistance(PositionComponent positionA, PositionComponent positionB) {
        float deltaX = positionB.x - positionA.x;
        float deltaY = positionB.y - positionA.y;

        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
