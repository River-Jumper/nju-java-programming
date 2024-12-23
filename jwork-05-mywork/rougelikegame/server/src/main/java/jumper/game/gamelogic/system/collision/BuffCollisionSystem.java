package jumper.game.gamelogic.system.collision;

import dev.dominion.ecs.api.Entity;
import jumper.game.gamelogic.component.buff.BuffComponent;
import jumper.game.gamelogic.component.destruction.DestructionComponent;
import jumper.game.gamelogic.component.health.AttackableComponent;
import jumper.game.gamelogic.component.health.HealthComponent;
import jumper.game.gamelogic.component.move.MovableComponent;
import jumper.game.gamelogic.component.shoot.ShootingComponent;
import jumper.game.gamelogic.component.singleton.CollisionEventManager;
import jumper.game.gamelogic.component.symbol.GiftComponent;
import jumper.game.gamelogic.component.symbol.PlayerComponent;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;

import static jumper.game.gamelogic.manager.SingletonManager.SingletonName.COLLISION_MESSAGE;

@RequiredArgsConstructor
public class BuffCollisionSystem implements Runnable {
    private final SystemContext context;
    @Override
    public void run() {
        CollisionEventManager collisionEventManager = context.singletonManager().getSingleton(COLLISION_MESSAGE);

        var collisionEvents = collisionEventManager.getEventWith(BuffComponent.class);
        while (collisionEvents != null  && !collisionEvents.isEmpty()) {
            var event = collisionEvents.poll();
            Entity entity1 = event.entity1();
            Entity entity2 = event.entity2();

            if (entity1.has(BuffComponent.class) && (!entity1.has(DestructionComponent.class))) {
                if (entity2.has(PlayerComponent.class)) {
                    entity1.add(new DestructionComponent());
                    this.dealWithBuff(entity2, entity1.get(BuffComponent.class));
                }
            }
            if (entity2.has(BuffComponent.class) && (!entity2.has(DestructionComponent.class))) {
                if (entity1.has(PlayerComponent.class)) {
                    entity2.add(new DestructionComponent());
                    this.dealWithBuff(entity1, entity2.get(BuffComponent.class));
                }
            }
        }
    }
    // shoot rate, health, moveSpeed
    public void dealWithBuff(Entity entity, BuffComponent buff) {
        if (entity.has(ShootingComponent.class)) {
            entity.get(ShootingComponent.class).shootInterval /= buff.shootRate();
            if (entity.get(ShootingComponent.class).shootInterval < 0.1) {
                entity.get(ShootingComponent.class).shootInterval = (float) 0.1;
            }
        }
        if (entity.has(HealthComponent.class)) {
            entity.get(HealthComponent.class).currentHealth += buff.additionalHealth();
        }
        if (entity.has(MovableComponent.class)) {
            entity.get(MovableComponent.class).maxSpeedX += buff.additionalSpeed();
            entity.get(MovableComponent.class).maxSpeedY += buff.additionalSpeed();
        }
        if (entity.has(AttackableComponent.class)) {
            entity.get(AttackableComponent.class).attack += buff.additionalDamage();
        }
    }
}
