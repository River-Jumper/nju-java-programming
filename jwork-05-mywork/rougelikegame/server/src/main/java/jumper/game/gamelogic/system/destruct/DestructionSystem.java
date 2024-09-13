package jumper.game.gamelogic.system.destruct;

import dev.dominion.ecs.api.Dominion;
import jumper.game.gamelogic.component.destruction.DestructionComponent;


public class DestructionSystem implements Runnable {
    private final Dominion world;
    public DestructionSystem(Dominion world) {
        this.world = world;
    }

    @Override
    public void run() {
        world.findEntitiesWith(DestructionComponent.class)
                .forEach(result -> world.deleteEntity(result.entity()));
    }
}
