package org.example.gamelogic.factory;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.example.GameConfig;
import org.example.gamelogic.component.*;

public class BulletFactory implements Factory{
    private Engine engine;
    @Override
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
    public void make(int positionX, int positionY, int speedX, int speedY) {
        Texture bulletTexture = new Texture(Gdx.files.internal("bullet/flower.png"));


        Entity bullet = new Entity();
        bullet.add(new BulletComponent());
        bullet.add(new PositionComponent(positionX, positionY));
        bullet.add(new MovableComponent(speedX, speedY));
        bullet.add(new CollisionComponent(GameConfig.BulletRADIUS));
        bullet.add(new ImageComponent(bulletTexture));

        engine.addEntity(bullet);
    }
}
