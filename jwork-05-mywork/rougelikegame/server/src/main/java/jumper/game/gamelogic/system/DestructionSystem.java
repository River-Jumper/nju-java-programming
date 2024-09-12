package jumper.game.gamelogic.system;

import dev.dominion.ecs.api.Dominion;
import jumper.game.gamelogic.component.DestructionComponent;


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
