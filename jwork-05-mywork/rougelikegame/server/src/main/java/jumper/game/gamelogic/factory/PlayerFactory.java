/*
package jumper.game.gamelogic.factory;

public class PlayerFactory implements Factory {
    private Engine engine;

    @Override
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
    public void make(int positionX, int positionY, int speedX, int speedY) {
        Texture playerTexture = new Texture(Gdx.files.internal("emoji/joker.png"));

        Entity player = new Entity();
        player.add(new PlayerComponent());
        player.add(new PositionComponent(positionX, positionY));
        player.add(new ImageComponent(playerTexture));
        player.add(new MovableComponent(speedX, speedY));
        player.add(new CollisionComponent(GameConfig.PlayerRADIUS));
        player.add(new InputMoveComponent());
        player.add(new ShootingComponent(GameConfig.ShootINTERVAL, GameConfig.ShootINTERVAL));
        player.add(new TargetComponent());

        this.engine.addEntity(player);
    }
}
*/