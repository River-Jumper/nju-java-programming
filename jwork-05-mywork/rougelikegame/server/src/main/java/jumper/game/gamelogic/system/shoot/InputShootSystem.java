
package org.example.gamelogic.system.shoot;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import dev.dominion.ecs.api.Entity;
import lombok.RequiredArgsConstructor;
import org.example.GameConfig;
import org.example.gamelogic.component.*;

import org.example.gamelogic.system.SystemContext;

@RequiredArgsConstructor
public class InputShootSystem implements Runnable {
    private SystemContext context;

    @Override
    public void run() {
        context.world().findEntitiesWith(PlayerComponent.class, ShootingComponent.class, PositionComponent.class, CollisionComponent.class)
                .forEach(result -> processEntity(result.entity()));
    }

    //Time get from timeComponent
    protected void processEntity(Entity entity) {
        ShootingComponent shootingComponent = entity.get(ShootingComponent.class);
        PositionComponent position = entity.get(PositionComponent.class);

        shootingComponent.currentInterval -= GameConfig.deltaTime;

        if (shoot() && shootingComponent.currentInterval <= 0) {
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();

            Vector3 screenCoords = new Vector3(mouseX, mouseY, 0);
            Vector3 worldCoords = context.camera().unproject(screenCoords);

            int worldX = (int) worldCoords.x;
            int worldY = (int) worldCoords.y;

            float deltaX = worldX - position.x;
            float deltaY = worldY - position.y;
            float deltaZ = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            int speedX = (int) ((deltaX / deltaZ) * GameConfig.BulletMAXSPEED);
            int speedY = (int) ((deltaY / deltaZ) * GameConfig.BulletMAXSPEED);

            int radius = entity.get(CollisionComponent.class).radius;
            int positionX = (int) ((deltaX / deltaZ) * (radius + 10) + position.x) ;
            int positionY = (int) ((deltaY / deltaZ) * (radius + 10) + position.y);


            context.bulletFactory().make(positionX, positionY, speedX, speedY);

            shootingComponent.currentInterval = shootingComponent.shootInterval;
        }
    }

    private boolean shoot() {
        return Gdx.input.isButtonPressed(Input.Buttons.LEFT);
    }
}
