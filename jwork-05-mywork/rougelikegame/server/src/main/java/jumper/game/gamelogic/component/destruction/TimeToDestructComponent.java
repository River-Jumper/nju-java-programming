package jumper.game.gamelogic.component.destruction;

public class TimeToDestructComponent {
    public float timeToDestruct;
    public TimeToDestructComponent(float timeToDestruct) {
        assert timeToDestruct > 0;
        this.timeToDestruct = timeToDestruct;
    }
}
