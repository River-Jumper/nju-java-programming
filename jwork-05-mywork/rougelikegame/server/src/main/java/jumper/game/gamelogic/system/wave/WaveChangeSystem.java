package jumper.game.gamelogic.system.wave;

import jumper.game.gamelogic.component.collision.CollisionComponent;
import jumper.game.gamelogic.component.move.PositionComponent;
import jumper.game.gamelogic.component.symbol.EnemyComponent;
import jumper.game.gamelogic.component.symbol.GiftComponent;
import jumper.game.gamelogic.system.SystemContext;
import jumper.game.gamelogic.system.WaveState;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class WaveChangeSystem implements Runnable {
    private final SystemContext context;
    @Override
    public void run() {
        if (context.waveState().state == WaveState.State.WAR) {
            boolean hasEnemy = false;
            var enemies = context.world().findEntitiesWith(EnemyComponent.class);
            for (var enemy : enemies) {
                hasEnemy = true;
                break;
            }
            if (!hasEnemy) {
                context.waveState().state = WaveState.State.REWARD;
            }
        }
        else if (context.waveState().state == WaveState.State.REWARD) {
            boolean hasGift = false;
            var gifts = context.world().findEntitiesWith(GiftComponent.class);
            for (var gift : gifts) {
                hasGift = true;
                break;
            }
            // gitFactory make the max sur time for gift is 2s
            if (!hasGift) {
                context.waveState().state = WaveState.State.WAR;
                context.waveState().waveNum++;
            }
        }
    }
}
