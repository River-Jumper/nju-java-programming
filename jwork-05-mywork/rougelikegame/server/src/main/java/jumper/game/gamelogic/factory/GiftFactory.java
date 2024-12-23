package jumper.game.gamelogic.factory;

import dev.dominion.ecs.api.Dominion;
import jumper.game.GameConfig;
import jumper.game.gamelogic.component.buff.BuffComponent;
import jumper.game.gamelogic.component.collision.CollisionComponent;
import jumper.game.gamelogic.component.destruction.TimeToDestructComponent;
import jumper.game.gamelogic.component.health.DamageComponent;
import jumper.game.gamelogic.component.health.HealthComponent;
import jumper.game.gamelogic.component.move.MovableComponent;
import jumper.game.gamelogic.component.move.PositionComponent;
import jumper.game.gamelogic.component.move.RandomMoveComponent;
import jumper.game.gamelogic.component.symbol.EnemyComponent;
import jumper.game.gamelogic.component.symbol.GiftComponent;
import jumper.game.gamelogic.component.symbol.SymbolComponent;
import network.FrameState;

import java.util.Random;

public class GiftFactory {
    public static void make(Dominion world, int positionX, int positionY,
                            int maxSpeedX, int maxSpeedY, float maxMoveInterval,
                            float shootRateBuff, int additionalHealthBuff, float additionalSpeedBuff,
                            int additionalDamage) {
        Random random = new Random();
        world.createEntity(
                new GiftComponent(),
                new PositionComponent(positionX, positionY),
                new SymbolComponent(FrameState.Symbol.GIFT),
                new CollisionComponent(GameConfig.GIFT_RADIUS),
                new MovableComponent(0, 0, maxSpeedX, maxSpeedY),
                new RandomMoveComponent(maxMoveInterval, random.nextFloat() * maxMoveInterval),
                new BuffComponent(shootRateBuff, additionalHealthBuff, additionalSpeedBuff, additionalDamage),
                new TimeToDestructComponent(5)
        );
    }
    public static void makeHealthGift(Dominion world, int positionX, int positionY,
                                      int maxSpeedX, int maxSpeedY, float maxMoveInterval) {
        GiftFactory.make(world, positionX, positionY,
                maxSpeedX, maxSpeedY, maxMoveInterval, 1, 1, 0, 0);
    }
    public static void makeShootRateGift(Dominion world, int positionX, int positionY,
                                      int maxSpeedX, int maxSpeedY, float maxMoveInterval, float shootRateBuff) {
        GiftFactory.make(world, positionX, positionY,
                maxSpeedX, maxSpeedY, maxMoveInterval, shootRateBuff, 1, 0, 0);
    }
    public static void makeSpeedGift(Dominion world, int positionX, int positionY,
                                      int maxSpeedX, int maxSpeedY, float maxMoveInterval, float additionalSpeedBuff) {
        GiftFactory.make(world, positionX, positionY,
                maxSpeedX, maxSpeedY, maxMoveInterval, 1, 1, additionalSpeedBuff, 0);
    }
    public static void makeDamageGift(Dominion world, int positionX, int positionY,
                                     int maxSpeedX, int maxSpeedY, float maxMoveInterval, int additionalDamageBuff) {
        GiftFactory.make(world, positionX, positionY,
                maxSpeedX, maxSpeedY, maxMoveInterval, 1, 1, 0, additionalDamageBuff);
    }
}
