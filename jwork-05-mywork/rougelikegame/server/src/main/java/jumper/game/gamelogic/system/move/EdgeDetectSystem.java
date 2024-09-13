package jumper.game.gamelogic.system.move;

import jumper.game.GameConfig;
import jumper.game.gamelogic.component.collision.CollisionComponent;
import jumper.game.gamelogic.component.move.PositionComponent;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class EdgeDetectSystem implements Runnable{
    private final SystemContext context;
    @Override
    public void run() {
        context.world().findCompositionsWith(PositionComponent.class, CollisionComponent.class)
                .forEach(result -> edgeDetection(result.comp1(), result.comp2().radius));
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
