package org.example.gamelogic.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.example.GameConfig;
import org.example.gamelogic.component.PositionComponent;
import org.example.gamelogic.component.ShootingComponent;
import org.example.gamelogic.factory.BulletFactory;

public class InputShootSystem extends IteratingSystem {
    private BulletFactory bulletFactory;

    public InputShootSystem(BulletFactory bulletFactory) {
        super(Family.all(ShootingComponent.class, PositionComponent.class).get());
        this.bulletFactory = bulletFactory;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ShootingComponent shootingComponent = entity.getComponent(ShootingComponent.class);
        PositionComponent position = entity.getComponent(PositionComponent.class);

        shootingComponent.currentInterval -= deltaTime;

        if (shoot() && shootingComponent.currentInterval <= 0) {
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();

            float deltaX = mouseX - position.x;
            float deltaY = mouseY - position.y;
            float deltaZ = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            int speedX = (int) ((GameConfig.BulletMAXSPEED / deltaZ) * deltaX);
            int speedY = (int) ((GameConfig.BulletMAXSPEED / deltaZ) * deltaY);


            this.bulletFactory.make((int) position.x, (int) position.y, speedX, speedY);

            shootingComponent.currentInterval = shootingComponent.shootInterval;
        }
    }

    private boolean shoot() {
        return Gdx.input.isButtonPressed(Input.Buttons.LEFT);
    }
}
