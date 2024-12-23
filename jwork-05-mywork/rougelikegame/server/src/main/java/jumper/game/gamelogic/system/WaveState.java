package jumper.game.gamelogic.system;

public class WaveState {
    public State state = State.WAR;
    public float waveTimeRemain = 10;
    public int waveNum = 1;
    public enum State {
        WAR,
        REWARD,
        DEFAULT
    }
}
