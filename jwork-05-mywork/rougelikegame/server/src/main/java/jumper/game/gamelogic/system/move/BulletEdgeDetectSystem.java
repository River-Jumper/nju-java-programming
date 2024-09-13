package jumper.game.gamelogic.system.move;


import jumper.game.GameConfig;
import jumper.game.gamelogic.component.symbol.BulletComponent;
import jumper.game.gamelogic.component.collision.CollisionComponent;
import jumper.game.gamelogic.component.destruction.DestructionComponent;
import jumper.game.gamelogic.component.move.PositionComponent;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BulletEdgeDetectSystem implements Runnable{
    private final SystemContext context;
    @Override
    public void run() {
        context.world().findEntitiesWith(BulletComponent.class, CollisionComponent.class, PositionComponent.class)
                .forEach(result -> {
                    if (edgeDetection(result.comp3(), result.comp2().radius)) {
                        result.entity().add(new DestructionComponent());
                    }
                });
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
