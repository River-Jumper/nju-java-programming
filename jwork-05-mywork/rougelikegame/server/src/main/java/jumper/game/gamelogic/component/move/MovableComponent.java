package jumper.game.gamelogic.component.move;



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
    public MovableComponent(int xSpeed, int ySpeed, int maxSpeedX, int maxSpeedY) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.maxSpeedX = maxSpeedX;
        this.maxSpeedY = maxSpeedY;
    }
    public MovableComponent(float xSpeed, float ySpeed, float maxSpeedX, float maxSpeedY) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.maxSpeedX = xSpeed;
        this.maxSpeedY = ySpeed;
    }
}

