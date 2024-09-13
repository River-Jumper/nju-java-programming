
package jumper.game.gamelogic.system.shoot;
import dev.dominion.ecs.api.Dominion;
import dev.dominion.ecs.api.Entity;
import dev.dominion.ecs.engine.IntEntity;
import jumper.game.GameConfig;
import jumper.game.gamelogic.component.collision.CollisionComponent;
import jumper.game.gamelogic.component.health.AttackableComponent;
import jumper.game.gamelogic.component.move.MovableComponent;
import jumper.game.gamelogic.component.symbol.BulletComponent;
import jumper.game.gamelogic.component.symbol.PlayerComponent;
import jumper.game.gamelogic.component.move.PositionComponent;
import jumper.game.gamelogic.component.shoot.ShootingComponent;
import jumper.game.gamelogic.factory.BulletFactory;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;
import network.MouseState;

@RequiredArgsConstructor
public class InputShootSystem implements Runnable {
    private SystemContext context;

    @Override
    public void run() {
        context.world().findCompositionsWith(PlayerComponent.class, ShootingComponent.class, PositionComponent.class, CollisionComponent.class)
                .forEach(result -> {
                    ShootingComponent shoot = result.comp2();
                    PositionComponent position = result.comp3();
                    int radius = result.comp4().radius;
                    int clientID = result.comp1().clientID;

                    shoot.shootInterval -= GameConfig.DELTA_TIME;
                    MouseState mouseState = context.inputManager().getMouseState(clientID);

                    if (shoot.shootInterval <= 0 && mouseState != null) {
                        if (mouseState.isClick()) {
                            int mouseX = (int) mouseState.x();
                            int mouseY = (int) mouseState.y();

                            float deltaX = mouseX - position.x;
                            float deltaY = mouseY - position.y;
                            float deltaZ = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

                            int speedX = (int) ((deltaX / deltaZ) * GameConfig.BULLET_MAX_SPEED);
                            int speedY = (int) ((deltaY / deltaZ) * GameConfig.BULLET_MAX_SPEED);

                            int positionX = (int) ((deltaX / deltaZ) * (radius + 10) + position.x);
                            int positionY = (int) ((deltaY / deltaZ) * (radius + 10) + position.y);

                            BulletFactory.make(context.world(), positionX, positionY, speedX, speedY);
                        }
                    }


                });
    }
}
