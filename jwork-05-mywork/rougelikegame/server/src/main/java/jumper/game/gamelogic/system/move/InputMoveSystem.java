/*
package jumper.game.gamelogic.system.move;

import dev.dominion.ecs.api.Entity;
import jumper.game.gamelogic.system.SystemContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class InputMoveSystem implements Runnable {
    private final SystemContext context;

    @Override
    public void run() {
        context.world().findEntitiesWith(InputMoveComponent.class)
                .forEach(result -> processEntity(result.entity()));
    }

    protected void processEntity(Entity entity) {
        if (entity.get(PlayerComponent.class) != null) {
            MovableComponent playSpeed = entity.get(MovableComponent.class);
            if (moveUp() || moveDown()) {
                if (moveUp()) {
                    playSpeed.ySpeed = GameConfig.PlayMAXSPEED;
                } else if (moveDown()) {
                    playSpeed.ySpeed = -(GameConfig.PlayMAXSPEED);
                }
            }
            else {
                playSpeed.ySpeed = 0;
            }
            if (moveLeft() || moveRight()) {
                if (moveRight()) {
                    playSpeed.xSpeed = GameConfig.PlayMAXSPEED;
                }
                else if (moveLeft()) {
                    playSpeed.xSpeed = -(GameConfig.PlayMAXSPEED);
                }
            }
            else {
                playSpeed.xSpeed = 0;
            }
        }
    }
    private boolean moveUp() {
        return Gdx.input.isKeyPressed(Input.Keys.W) ||  Gdx.input.isKeyPressed(Input.Keys.NUMPAD_8) ||  Gdx.input.isKeyPressed(Input.Keys.UP);
    }
    private boolean moveDown() {
        return Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2) ||  Gdx.input.isKeyPressed(Input.Keys.DOWN);
    }
    private boolean moveLeft() {
        return Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4) ||  Gdx.input.isKeyPressed(Input.Keys.LEFT);
    }
    private boolean moveRight() {
        return Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6) ||  Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    }


}
*/

