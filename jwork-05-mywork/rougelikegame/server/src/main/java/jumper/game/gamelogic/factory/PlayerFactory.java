package jumper.game.gamelogic.factory;

import dev.dominion.ecs.api.Dominion;
import jumper.game.GameConfig;
import jumper.game.gamelogic.component.collision.CollisionComponent;
import jumper.game.gamelogic.component.health.AttackableComponent;
import jumper.game.gamelogic.component.move.InputMoveComponent;
import jumper.game.gamelogic.component.move.MovableComponent;
import jumper.game.gamelogic.component.move.PositionComponent;
import jumper.game.gamelogic.component.move.RandomMoveComponent;
import jumper.game.gamelogic.component.shoot.ShootingComponent;
import jumper.game.gamelogic.component.symbol.PlayerComponent;
import jumper.game.gamelogic.component.symbol.SymbolComponent;
import network.FrameState;

public class PlayerFactory {
    public static void make(Dominion world, int clientID) {
        world.createEntity(
                new PositionComponent( clientID * 100, clientID * 50),
                new SymbolComponent(FrameState.Symbol.PLAYER),
                new CollisionComponent(GameConfig.PLAYER_RADIUS),
                new MovableComponent(10, 10, 100, 100),
                new InputMoveComponent(),
                new ShootingComponent(1, 0),
                new AttackableComponent(1),
                new PlayerComponent(clientID)
        );
    }
}
