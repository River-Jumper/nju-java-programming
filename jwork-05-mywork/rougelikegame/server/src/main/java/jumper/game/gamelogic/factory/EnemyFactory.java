package jumper.game.gamelogic.factory;

import dev.dominion.ecs.api.Dominion;
import jumper.game.GameConfig;
import jumper.game.gamelogic.component.collision.CollisionComponent;
import jumper.game.gamelogic.component.health.DamageComponent;
import jumper.game.gamelogic.component.health.HealthComponent;
import jumper.game.gamelogic.component.move.InputMoveComponent;
import jumper.game.gamelogic.component.move.MovableComponent;
import jumper.game.gamelogic.component.move.PositionComponent;
import jumper.game.gamelogic.component.move.RandomMoveComponent;
import jumper.game.gamelogic.component.shoot.ShootingComponent;
import jumper.game.gamelogic.component.symbol.EnemyComponent;
import jumper.game.gamelogic.component.symbol.PlayerComponent;
import jumper.game.gamelogic.component.symbol.SymbolComponent;
import network.FrameState;

import java.util.Random;

public class EnemyFactory {
    public static void make(Dominion world, int positionX, int positionY,
                            int maxSpeedX, int maxSpeedY, float maxMoveInterval,
                            int health) {
        Random random = new Random();
        world.createEntity(
                new EnemyComponent(),
                new PositionComponent(positionX, positionY),
                new SymbolComponent(FrameState.Symbol.ENEMY),
                new CollisionComponent(GameConfig.ENEMY_RADIUS),
                new MovableComponent(0, 0, maxSpeedX, maxSpeedY),
                new RandomMoveComponent(maxMoveInterval, random.nextFloat() * maxMoveInterval),
                new HealthComponent(health),
                new DamageComponent()
        );

    }
}
