package jumper.game.gamelogic.system.wave;

import jumper.game.GameConfig;
import jumper.game.gamelogic.component.move.MovableComponent;
import jumper.game.gamelogic.factory.GiftFactory;
import jumper.game.gamelogic.system.SystemContext;
import jumper.game.gamelogic.system.WaveState;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class GiftWaveSystem implements Runnable {
    private final SystemContext context;
    private WaveState.State curState = WaveState.State.DEFAULT;
    @Override
    public void run() {
        if (context.waveState().state != this.curState
                && context.waveState().state == WaveState.State.REWARD) {
            GiftFactory.makeDamageGift(context.world(), 300, 360,
                    GameConfig.GIFT_MAX_SPEED, GameConfig.GIFT_MAX_SPEED, 1, 1);
            GiftFactory.makeSpeedGift(context.world(), 600, 360,
                    GameConfig.GIFT_MAX_SPEED, GameConfig.GIFT_MAX_SPEED, 1, 30);
            GiftFactory.makeShootRateGift(context.world(), 900, 360,
                    GameConfig.GIFT_MAX_SPEED, GameConfig.GIFT_MAX_SPEED, 1, 2);
        }
        this.curState = context.waveState().state;
    }
}
