package jumper.game.gamelogic.system.collision;

import jumper.game.gamelogic.component.singleton.CollisionEventManager;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;

import static jumper.game.SingletonManager.SingletonName.COLLISION_MESSAGE;


//work after all other System in collision
@RequiredArgsConstructor
public class ClearCollisionEventsSystem implements Runnable{
    private final SystemContext context;
    @Override
    public void run() {
        CollisionEventManager collisionEventManager = context.singletonManager().getSingleton(COLLISION_MESSAGE);
        collisionEventManager.clearEvent();
    }
}
