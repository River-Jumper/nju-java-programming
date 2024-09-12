package jumper.game.gamelogic.component;



public class ShootingComponent{
    public float shootInterval;
    public float currentInterval;
    public ShootingComponent(float shootInterval, float currentInterval) {
        this.shootInterval = shootInterval;
        this.currentInterval = currentInterval;
    }
}
