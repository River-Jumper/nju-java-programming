package org.example.gamelogic.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.example.GameConfig;
import org.example.gamelogic.component.KeyboardInputComponent;
import org.example.gamelogic.component.MovableComponent;
import org.example.gamelogic.component.PlayerComponent;

public class KeyboardInputSystem extends IteratingSystem {
    public KeyboardInputSystem() {
        super(Family.all(KeyboardInputComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        if (entity.getComponent(PlayerComponent.class) != null) {
            MovableComponent playSpeed = entity.getComponent(MovableComponent.class);
            //press W
            if (moveUp() || moveDown() || moveLeft() || moveRight()) {
                if (moveUp()) {
                    playSpeed.ySpeed = GameConfig.PlayMAXSPEED;
                }
                if (moveDown()) {
                    playSpeed.ySpeed = -(GameConfig.PlayMAXSPEED);
                }
                if (moveRight()) {
                    playSpeed.xSpeed = GameConfig.PlayMAXSPEED;
                }
                if (moveLeft()) {
                    playSpeed.xSpeed = -(GameConfig.PlayMAXSPEED);
                }
            }
            else {
                playSpeed.ySpeed = 0;
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
