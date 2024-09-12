package jumper.game.gamelogic.system.health;


import jumper.game.gamelogic.component.DamageComponent;
import jumper.game.gamelogic.component.HealthComponent;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;


import java.util.Set;

@RequiredArgsConstructor
public class DamageSystem implements Runnable{
    private final SystemContext context;

    @Override
    public void run() {
        context.world().findCompositionsWith(DamageComponent.class, HealthComponent.class)
                .forEach(result -> {
                    int damage = result.comp1().damage;
                    result.comp2().currentHealth -= damage;
                });
    }
}
