package jumper.game.gamelogic.system.health;

import jumper.game.gamelogic.component.destruction.DestructionComponent;
import jumper.game.gamelogic.component.health.HealthComponent;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class HealthSystem implements Runnable {

    private final SystemContext context;

    @Override
    public void run() {
        context.world().findEntitiesWith(HealthComponent.class)
                .forEach(result -> {
                    HealthComponent healthComponent = result.comp();
                    if (healthComponent.currentHealth > healthComponent.maxHealth) {
                        healthComponent.currentHealth = healthComponent.maxHealth;
                    }
                    if (healthComponent.currentHealth <= 0) {
                        result.entity().add(new DestructionComponent());
                    }
                });
    }
}
