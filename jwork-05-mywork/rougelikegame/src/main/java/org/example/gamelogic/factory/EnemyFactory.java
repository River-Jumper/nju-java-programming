package org.example.gamelogic.factory;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.example.GameConfig;
import org.example.gamelogic.component.*;

public class EnemyFactory implements Factory{
    private Engine engine;

    @Override
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
    public void make(int positionX, int positionY, int speedX, int speedY) {
        Texture enemyTexture = new Texture(Gdx.files.internal("emoji/ghost.png"));

        Entity enemy = new Entity();
        enemy.add(new EnemyComponent());
        enemy.add(new PositionComponent(positionX, positionY));
        enemy.add(new ImageComponent(enemyTexture));
        enemy.add(new MovableComponent(speedX, speedY));
        enemy.add(new CollisionComponent(GameConfig.EnemyRADIUS));

        this.engine.addEntity(enemy);
    }
}
