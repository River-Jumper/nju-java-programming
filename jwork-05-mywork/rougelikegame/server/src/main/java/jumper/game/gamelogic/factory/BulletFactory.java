
package jumper.game.gamelogic.factory;


import dev.dominion.ecs.api.Dominion;
import jumper.game.GameConfig;
import jumper.game.gamelogic.component.collision.CollisionComponent;
import jumper.game.gamelogic.component.health.AttackableComponent;
import jumper.game.gamelogic.component.move.MovableComponent;
import jumper.game.gamelogic.component.move.PositionComponent;
import jumper.game.gamelogic.component.symbol.BulletComponent;
import jumper.game.gamelogic.component.symbol.SymbolComponent;
import network.FrameState;

public class BulletFactory{
    public static void make(Dominion world, int positionX, int positionY, int speedX, int speedY) {
        world.createEntity(
                new BulletComponent(),
                new CollisionComponent(15),
                new PositionComponent(positionX, positionY),
                new MovableComponent(speedX, speedY),
                new AttackableComponent(GameConfig.BULLET_ATTACK),
                new SymbolComponent(FrameState.Symbol.BULLET)
        );
    }
}
