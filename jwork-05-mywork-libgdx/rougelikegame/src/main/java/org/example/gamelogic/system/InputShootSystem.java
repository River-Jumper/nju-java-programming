package org.example.gamelogic.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import org.example.GameConfig;
import org.example.gamelogic.component.CollisionComponent;
import org.example.gamelogic.component.PositionComponent;
import org.example.gamelogic.component.ShootingComponent;
import org.example.gamelogic.factory.BulletFactory;

import java.util.Vector;

public class InputShootSystem extends IteratingSystem {
    private final BulletFactory bulletFactory;
    private final OrthographicCamera camera;

    public InputShootSystem(BulletFactory bulletFactory, OrthographicCamera camera) {
        super(Family.all(ShootingComponent.class, PositionComponent.class).get());
        this.bulletFactory = bulletFactory;
        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ShootingComponent shootingComponent = entity.getComponent(ShootingComponent.class);
        PositionComponent position = entity.getComponent(PositionComponent.class);

        shootingComponent.currentInterval -= deltaTime;

        if (shoot() && shootingComponent.currentInterval <= 0) {
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();

            Vector3 screenCoords = new Vector3(mouseX, mouseY, 0);
            Vector3 worldCoords = camera.unproject(screenCoords);

            int worldX = (int) worldCoords.x;
            int worldY = (int) worldCoords.y;

            float deltaX = worldX - position.x;
            float deltaY = worldY - position.y;
            float deltaZ = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            int speedX = (int) ((deltaX / deltaZ) * GameConfig.BulletMAXSPEED);
            int speedY = (int) ((deltaY / deltaZ) * GameConfig.BulletMAXSPEED);

            int radius = entity.getComponent(CollisionComponent.class).radius;
            int positionX = (int) ((deltaX / deltaZ) * (radius + 10) + position.x) ;
            int positionY = (int) ((deltaY / deltaZ) * (radius + 10) + position.y);


            this.bulletFactory.make(positionX, positionY, speedX, speedY);

            shootingComponent.currentInterval = shootingComponent.shootInterval;
        }
    }

    private boolean shoot() {
        return Gdx.input.isButtonPressed(Input.Buttons.LEFT);
    }
}
