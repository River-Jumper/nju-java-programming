
package jumper.game.gamelogic.system.move;
import jumper.game.GameConfig;
import jumper.game.gamelogic.component.MovableComponent;
import jumper.game.gamelogic.component.RandomMoveComponent;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;


import java.util.Random;

@RequiredArgsConstructor
public class RandomMoveSystem implements Runnable {
    private final SystemContext context;

    @Override
    public void run() {
        context.world().findCompositionsWith(RandomMoveComponent.class, MovableComponent.class)
                .forEach(result -> processRandomMove(result.comp1(), result.comp2()));
    }

    protected void processRandomMove(RandomMoveComponent randomMove, MovableComponent movement) {
        if (randomMove.currentInterval > 0) {
            randomMove.currentInterval -= GameConfig.DELTA_TIME;
        }
        else {
            Random random = new Random();
            float config = (float) 2 / 3;
            float speedX = (2 * random.nextInt((int)movement.maxSpeedX) - (int)movement.maxSpeedX) * config;
            float speedY = (2 * random.nextInt((int)movement.maxSpeedY) - (int)movement.maxSpeedY) * config;

            movement.xSpeed = speedX;
            movement.ySpeed = speedY;

            randomMove.currentInterval = randomMove.maxInterval;
        }
    }
}
