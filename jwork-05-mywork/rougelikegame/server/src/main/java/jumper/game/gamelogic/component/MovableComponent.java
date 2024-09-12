package jumper.game.gamelogic.component;



public class MovableComponent{
    public float xSpeed;
    public float ySpeed;
    public float maxSpeedX;
    public float maxSpeedY;

    public MovableComponent(int xSpeed, int ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.maxSpeedX = xSpeed;
        this.maxSpeedY = ySpeed;
    }
    public MovableComponent(float xSpeed, float ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.maxSpeedX = xSpeed;
        this.maxSpeedY = ySpeed;
    }
}

