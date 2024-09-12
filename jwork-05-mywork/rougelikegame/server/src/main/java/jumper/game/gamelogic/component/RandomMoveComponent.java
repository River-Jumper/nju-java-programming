package jumper.game.gamelogic.component;



public class RandomMoveComponent{
    public float maxInterval;
    public float currentInterval;

    public RandomMoveComponent(float maxInterval) {
        this.maxInterval = maxInterval;
        this.currentInterval = maxInterval;
    }
    public RandomMoveComponent(float maxInterval, float currentInterval) {
        this.maxInterval = maxInterval;
        this.currentInterval = currentInterval;
    }
}
