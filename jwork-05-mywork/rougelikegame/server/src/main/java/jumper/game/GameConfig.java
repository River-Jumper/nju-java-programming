package jumper.game;

public class GameConfig {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 720;

    public static final int PLAYER_RADIUS = 25;
    public static final int ENEMY_RADIUS = 25;
    public static final int BULLET_RADIUS = 14;
    public static final int DEFAULT_RADIUS = 18;

    public static final int PLAY_MAX_SPEED = 30;
    public static final int ENEMY_MAX_SPEED = 20;
    public static final int BULLET_MAX_SPEED = 150;

    public static final float PLAY_SHOOT_INTERVAL = 0.5F;

    public static final int ENEMY_TARGET_DISTANCE = 300;

    //deltaTime
    public static final float DELTA_TIME = (float) 1 / 60;
}
