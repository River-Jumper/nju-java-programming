package org.example.gamelogic.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.example.FinalClass;
import org.example.gamelogic.component.EnemyComponent;
import org.example.gamelogic.component.ImageComponent;
import org.example.gamelogic.component.PlayerComponent;
import org.example.gamelogic.component.PositionComponent;

public class RenderSystem extends IteratingSystem {
    private final SpriteBatch batch;

    public RenderSystem(SpriteBatch batch) {
        super(Family.all(PositionComponent.class, ImageComponent.class).get());
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

        if (entity.getComponent(PlayerComponent.class) != null) {
            this.batch.draw(texture, position.x, position.y, 2 * FinalClass.PlayerRADIUS, 2 * FinalClass.PlayerRADIUS);
        }
        else if (entity.getComponent(EnemyComponent.class) != null) {
            this.batch.draw(texture, position.x, position.y, 2 * FinalClass.EnemyRADIUS, 2 * FinalClass.EnemyRADIUS);
        }
        else {
            this.batch.draw(texture, position.x, position.y, 2 * FinalClass.DefaultRADIUS, 2 * FinalClass.DefaultRADIUS);
        }

    }
}
