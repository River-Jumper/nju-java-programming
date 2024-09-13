package jumper.game.gamelogic.component.shoot;



public class ShootingComponent{
    public float shootInterval;
    public float currentInterval;
    public ShootingComponent(float shootInterval, float currentInterval) {
        this.shootInterval = shootInterval;
        this.currentInterval = currentInterval;
    }
}
