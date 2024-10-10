package jumper.game.gamelogic.system.collision;

import dev.dominion.ecs.api.Entity;
import jumper.game.gamelogic.component.health.AttackableComponent;
import jumper.game.gamelogic.component.health.DamageComponent;
import jumper.game.gamelogic.component.singleton.CollisionEventManager;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;

import static jumper.game.gamelogic.manager.SingletonManager.SingletonName.COLLISION_MESSAGE;

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

            if (entity1.has(AttackableComponent.class) && entity2.has(DamageComponent.class)) {
                int attack = entity1.get(AttackableComponent.class).attack;
                entity2.get(DamageComponent.class).damage += attack;
            }
            if (entity2.has(AttackableComponent.class) && entity1.has(DamageComponent.class)) {
                int attack = entity2.get(AttackableComponent.class).attack;
                entity1.get(DamageComponent.class).damage += attack;
            }
        }
    }
}
