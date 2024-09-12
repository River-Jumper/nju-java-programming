package jumper.game.gamelogic.component;


public class TargetMoveComponent{
    public int targetX;
    public int targetY;

    //if distance between this entity and target <= targetDistance, this entity will
    //move towards target
    public int targetDistance;

    public TargetMoveComponent(int targetDistance) {
        this.targetDistance = targetDistance;
    }
}
