package jumper.game.gamelogic.system.collision;

import dev.dominion.ecs.api.Entity;
import jumper.game.gamelogic.component.AttackableComponent;
import jumper.game.gamelogic.component.DamageComponent;
import jumper.game.gamelogic.component.singleton.CollisionEventManager;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;

import static jumper.game.SingletonManager.SingletonName.COLLISION_MESSAGE;

@RequiredArgsConstructor
public class CollisionDamageSystem implements Runnable{
    private final SystemContext context;
    @Override
    public void run() {
        CollisionEventManager collisionEventManager = context.singletonManager().getSingleton(COLLISION_MESSAGE);

        var collisionEvents = collisionEventManager.getEventWith(AttackableComponent.class);

        while (collisionEvents != null  && !collisionEvents.isEmpty()) {
            var event = collisionEvents.poll();
            Entity entity1 = event.entity1();
            Entity entity2 = event.entity2();

            if (entity1.has(AttackableComponent.class)) {
                int attack = entity1.get(AttackableComponent.class).attack;
                event.entity2().add(new DamageComponent(attack));
            }
            if (entity2.has(AttackableComponent.class)) {
                int attack = entity2.get(AttackableComponent.class).attack;
                event.entity1().add(new DamageComponent(attack));
            }
        }
    }
}
