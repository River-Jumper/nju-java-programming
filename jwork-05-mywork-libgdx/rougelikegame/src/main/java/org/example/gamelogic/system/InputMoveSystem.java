package org.example.gamelogic.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.example.GameConfig;
import org.example.gamelogic.component.InputMoveComponent;
import org.example.gamelogic.component.MovableComponent;
import org.example.gamelogic.component.PlayerComponent;

public class InputMoveSystem extends IteratingSystem {
    public InputMoveSystem() {
        super(Family.all(InputMoveComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        if (entity.getComponent(PlayerComponent.class) != null) {
            MovableComponent playSpeed = entity.getComponent(MovableComponent.class);
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
