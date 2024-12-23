package jumper.game.gamelogic.system.destruct;

import jumper.game.GameConfig;
import jumper.game.gamelogic.component.destruction.DestructionComponent;
import jumper.game.gamelogic.component.destruction.TimeToDestructComponent;
import jumper.game.gamelogic.component.health.HealthComponent;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TimeToDestructSystem implements Runnable {
    private final SystemContext context;

    @Override
    public void run() {
        context.world().findEntitiesWith(TimeToDestructComponent.class)
                .forEach(result -> {
                    TimeToDestructComponent timeToDestructComponent = result.comp();
                    timeToDestructComponent.timeToDestruct -= GameConfig.DELTA_TIME;
                    if (timeToDestructComponent.timeToDestruct <= 0) {
                        result.entity().add(new DestructionComponent());
                    }
                });
    }
}
