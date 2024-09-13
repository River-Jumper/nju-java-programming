
package jumper.game.gamelogic.system.move;

import dev.dominion.ecs.api.Entity;
import jumper.game.GameConfig;
import jumper.game.gamelogic.component.move.InputMoveComponent;
import jumper.game.gamelogic.component.move.MovableComponent;
import jumper.game.gamelogic.component.symbol.PlayerComponent;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;
import network.KeyboardState;


@RequiredArgsConstructor
public class InputMoveSystem implements Runnable {
    private final SystemContext context;

    @Override
    public void run() {
        context.world().findCompositionsWith(InputMoveComponent.class, MovableComponent.class, PlayerComponent.class)
                .forEach(result -> {
                    KeyboardState keyboardState = context.inputManager().
                            getKeyboardState(result.comp3().clientID);
                    MovableComponent playSpeed = result.comp2();
                    if (keyboardState != null) {
                        if (keyboardState.up() || keyboardState.down()) {
                            if (keyboardState.up()) {
                                playSpeed.ySpeed = GameConfig.PLAY_MAX_SPEED;
                            } else {
                                playSpeed.ySpeed = -(GameConfig.PLAY_MAX_SPEED);
                            }
                        }
                        else {
                            playSpeed.ySpeed = 0;
                        }

                        if (keyboardState.left() || keyboardState.right()) {
                            if (keyboardState.right()) {
                                playSpeed.xSpeed = GameConfig.PLAY_MAX_SPEED;
                            }
                            else {
                                playSpeed.xSpeed = -(GameConfig.PLAY_MAX_SPEED);
                            }
                        }
                        else {
                            playSpeed.xSpeed = 0;
                        }
                    }
                });
    }

}


