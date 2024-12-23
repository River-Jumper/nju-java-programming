package jumper.game.gamelogic.system.wave;

import jumper.game.GameConfig;
import jumper.game.gamelogic.component.move.MovableComponent;
import jumper.game.gamelogic.system.SystemContext;
import jumper.game.gamelogic.system.WaveState;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class WaveTimerSystem implements Runnable {
    private final SystemContext context;

    @Override
    public void run() {
        context.waveState().waveTimeRemain -= GameConfig.DELTA_TIME;
        if (context.waveState().waveTimeRemain <= 0 && context.waveState().state == WaveState.State.WAR) {
            log.info("Time out! G G W P");
        }
    }
}
