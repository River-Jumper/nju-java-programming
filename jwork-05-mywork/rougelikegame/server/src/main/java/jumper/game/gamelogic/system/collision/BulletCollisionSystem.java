package jumper.game.gamelogic.system.collision;

import dev.dominion.ecs.api.Entity;
import jumper.game.gamelogic.component.symbol.BulletComponent;
import jumper.game.gamelogic.component.destruction.DestructionComponent;
import jumper.game.gamelogic.component.singleton.CollisionEventManager;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;

import static jumper.game.gamelogic.manager.SingletonManager.SingletonName.COLLISION_MESSAGE;

//to destruction bullet
@RequiredArgsConstructor
public class BulletCollisionSystem implements Runnable {
    private final SystemContext context;


    @Override
    public void run() {
        CollisionEventManager collisionEventManager = context.singletonManager().getSingleton(COLLISION_MESSAGE);

        var collisionEvents = collisionEventManager.getEventWith(BulletComponent.class);
        while (collisionEvents != null  && !collisionEvents.isEmpty()) {
            var event = collisionEvents.poll();
            Entity entity1 = event.entity1();
            Entity entity2 = event.entity2();

            if (entity1.has(BulletComponent.class) && (!entity1.has(DestructionComponent.class))) {
                entity1.add(new DestructionComponent());
            }
            if (entity2.has(BulletComponent.class) && (!entity2.has(DestructionComponent.class))) {
                entity2.add(new DestructionComponent());
            }
        }
    }
}
