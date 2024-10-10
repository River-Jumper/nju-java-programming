package jumper.game.gamelogic.system.destruct;

import dev.dominion.ecs.api.Dominion;
import jumper.game.gamelogic.component.destruction.DestructionComponent;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DestructionSystem implements Runnable {
    private final SystemContext context;

    @Override
    public void run() {
        context.world().findEntitiesWith(DestructionComponent.class)
                .forEach(result ->context.world().deleteEntity(result.entity()));
    }
}
