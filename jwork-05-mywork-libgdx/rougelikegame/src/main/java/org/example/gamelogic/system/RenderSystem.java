package org.example.gamelogic.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.example.GameConfig;
import org.example.gamelogic.component.*;

public class RenderSystem extends IteratingSystem {
    private final SpriteBatch batch;

    public RenderSystem(SpriteBatch batch) {
        super(Family.all(PositionComponent.class, ImageComponent.class, CollisionComponent.class).get());
        this.batch = batch;
    }

    @Override
    public void update(float v) {
        this.batch.begin();
        super.update(v);
        this.batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        Texture texture = entity.getComponent(ImageComponent.class).texture;
        PositionComponent position = entity.getComponent(PositionComponent.class);
        int radius = entity.getComponent(CollisionComponent.class).radius;

        this.batch.draw(texture, position.x, position.y, 2 * radius, 2 * radius);
    }
}
