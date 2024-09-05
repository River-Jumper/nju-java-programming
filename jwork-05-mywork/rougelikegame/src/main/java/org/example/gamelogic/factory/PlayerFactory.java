package org.example.gamelogic.factory;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.example.GameConfig;
import org.example.gamelogic.component.*;

public class PlayerFactory implements Factory {
    private Engine engine;

    @Override
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
    public void make(int positionX, int positionY, int speedX, int speedY) {
        Texture playerTexture = new Texture(Gdx.files.internal("emoji/player.png"));

        Entity player = new Entity();
        player.add(new PlayerComponent());
        player.add(new PositionComponent(positionX, positionY));
        player.add(new ImageComponent(playerTexture));
        player.add(new MovableComponent(speedX, speedY));
        player.add(new CollisionComponent(GameConfig.PlayerRADIUS));
        player.add(new InputMoveComponent());
        player.add(new ShootingComponent(GameConfig.ShootINTERVAL, GameConfig.ShootINTERVAL));

        this.engine.addEntity(player);
    }
}
