package jumper.game.gamelogic.system.move;
import jumper.game.GameConfig;
import jumper.game.gamelogic.component.MovableComponent;
import jumper.game.gamelogic.component.PositionComponent;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class MovementSystem implements Runnable {
    private final SystemContext context;

    @Override
    public void run() {
        context.world().findCompositionsWith(MovableComponent.class, PositionComponent.class)
                .forEach(result -> {
                    result.comp2().x += GameConfig.DELTA_TIME * result.comp1().xSpeed;
                    result.comp2().y += GameConfig.DELTA_TIME * result.comp1().ySpeed;
                });
    }

}
