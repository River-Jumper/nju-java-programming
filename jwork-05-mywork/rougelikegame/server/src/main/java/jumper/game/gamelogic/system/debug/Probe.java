package jumper.game.gamelogic.system.debug;

import jumper.game.gamelogic.component.destruction.DestructionComponent;
import jumper.game.gamelogic.component.move.MovableComponent;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class Probe implements Runnable {
    private final SystemContext context;
    @Override
    public void run() {
        context.world().findEntitiesWith(MovableComponent.class)
                .forEach(result -> {
                    System.out.printf("xSpeed = %f, ySpeed = %f", result.comp().xSpeed, result.comp().ySpeed);
                });
    }
}
