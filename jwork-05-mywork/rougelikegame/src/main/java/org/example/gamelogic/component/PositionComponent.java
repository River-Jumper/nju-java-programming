package org.example.gamelogic.component;

public class PositionComponent {
    private int x;
    private int y;

    public PositionComponent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setXAndY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
