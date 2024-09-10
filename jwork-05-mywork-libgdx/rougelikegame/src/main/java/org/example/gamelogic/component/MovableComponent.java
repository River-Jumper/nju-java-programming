package org.example.gamelogic.component;

import com.badlogic.ashley.core.Component;

public class MovableComponent implements Component {
    public float xSpeed;
    public float ySpeed;

    public MovableComponent(int xSpeed, int ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    public MovableComponent(float xSpeed, float ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
}

